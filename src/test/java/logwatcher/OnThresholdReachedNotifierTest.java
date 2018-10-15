package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

class OnThresholdReachedNotifierTest {

	@Test
	void shouldNotifyAfterThresholdReached() {
		AtomicInteger notificationsSentWith2Threshold = new AtomicInteger(0);
		AtomicInteger notificationsSentWith3Threshold = new AtomicInteger(0);
		AtomicInteger notificationsSentWith5Threshold = new AtomicInteger(0);

		OnThresholdReachedNotifier notifierDecoratorWith2Threshold = new OnThresholdReachedNotifier(2, notificationsSentWith2Threshold::incrementAndGet);
		OnThresholdReachedNotifier notifierDecoratorWith3Threshold = new OnThresholdReachedNotifier(3, notificationsSentWith3Threshold::incrementAndGet);
		OnThresholdReachedNotifier notifierDecoratorWith5Threshold = new OnThresholdReachedNotifier(5, notificationsSentWith5Threshold::incrementAndGet);

		List<LogEntryWithContext> exampleMessages = List.of(
				new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>()),
				new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>()),
				new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>()),
				new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>()),
				new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>()),
				new LogEntryWithContext(new LogEntry(0, "test", LogLevel.INFO), new ArrayDeque<>())
		);

		exampleMessages.forEach(message -> {
			notifierDecoratorWith2Threshold.notify(message);
			notifierDecoratorWith3Threshold.notify(message);
			notifierDecoratorWith5Threshold.notify(message);
		});

		assertEquals(2, notificationsSentWith2Threshold.get());
		assertEquals(1, notificationsSentWith3Threshold.get());
		assertEquals(1, notificationsSentWith5Threshold.get());
	}

}