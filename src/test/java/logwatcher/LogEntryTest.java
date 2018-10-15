package logwatcher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logwatcher.LogEntry;
import logwatcher.LogLevel;

class LogEntryTest {

	@Test
	void shouldHoldAndReturnState() {
		LogEntry example = new LogEntry(12, "Test Text", LogLevel.ERROR);
		assertEquals(LogLevel.ERROR, example.getSeverity());
		assertEquals(12, example.getCode());
		assertEquals("Test Text", example.getText());
	}
}