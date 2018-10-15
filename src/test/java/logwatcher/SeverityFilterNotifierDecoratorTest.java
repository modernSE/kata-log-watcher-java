package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class SeverityFilterNotifierDecoratorTest {

	@Test
	void shouldOnlyLetFilteredMessagesThrough() {
		List<LogEntryWithContext> forwardedContexts = new ArrayList<>();

		SeverityFilterNotifierDecorator decorator = new SeverityFilterNotifierDecorator(context -> {
			forwardedContexts.add(context);
		}, Set.of(LogLevel.ERROR, LogLevel.INFO));

		LogEntryWithContext infoMessage = new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>());
		LogEntryWithContext debugMessage = new LogEntryWithContext(new LogEntry(0, "test", LogLevel.DEBUG), new ArrayDeque<>());
		LogEntryWithContext errorMessage = new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>());
		LogEntryWithContext traceMessage = new LogEntryWithContext(new LogEntry(0, "test", LogLevel.TRACE), new ArrayDeque<>());

		decorator.notify(infoMessage);
		decorator.notify(debugMessage);
		decorator.notify(errorMessage);
		decorator.notify(traceMessage);

		assertEquals(2, forwardedContexts.size());

		assertTrue(forwardedContexts.contains(infoMessage));
		assertTrue(forwardedContexts.contains(errorMessage));
	}

}