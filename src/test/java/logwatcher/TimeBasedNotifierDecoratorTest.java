package logwatcher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

class TimeBasedNotifierDecoratorTest {

	@Test
	void shouldDelegateIfPredicateApplies() {
		AtomicBoolean notified = new AtomicBoolean(false);

		new TimeBasedNotifierDecorator(Clock.systemDefaultZone(), context -> notified.set(true), clock -> true).notify(new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>()));

		assertTrue(notified.get());
	}

	@Test
	void shouldNotDelegateIfPredicateDoesNotApplies() {
		AtomicBoolean notified = new AtomicBoolean(false);

		new TimeBasedNotifierDecorator(Clock.systemDefaultZone(), context -> notified.set(true), clock -> false).notify(new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>()));

		assertFalse(notified.get());
	}

}