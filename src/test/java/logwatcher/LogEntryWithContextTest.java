package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.Test;

class LogEntryWithContextTest {

	@Test
	void shouldHoldAndReturnState() {
		LogEntry logEntry = new LogEntry(12, "Test", LogLevel.ERROR);
		LogEntry historicLogEntry = new LogEntry(0, "Test", LogLevel.INFO);

		LogEntryWithContext logEntryWithContext = new LogEntryWithContext(logEntry, new ArrayDeque<>(List.of(historicLogEntry)));

		assertSame(logEntry, logEntryWithContext.getCurrent());
		assertSame(historicLogEntry, logEntryWithContext.getPreviousMessagesForLevel(LogLevel.INFO).get(0));
		assertSame(historicLogEntry, logEntryWithContext.getHistory().getFirst());
		assertEquals(1, logEntryWithContext.getHistory().size());
	}

}