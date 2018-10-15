package logwatcher;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

class CompositeNotifierTest {

	@Test
	void delegatesToAllNotifiers() {
		LogEntryWithContext logEntryWithContext = new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>());

		AtomicBoolean notifier1Notified = new AtomicBoolean();
		AtomicBoolean notifier2Notified = new AtomicBoolean();

		CompositeNotifier compositeNotifier = new CompositeNotifier(List.of(context -> notifier1Notified.set(true), context -> notifier2Notified.set(true)));

		compositeNotifier.notify(logEntryWithContext);

		assertTrue(notifier1Notified.get());
		assertTrue(notifier2Notified.get());
	}

}