package logwatcher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

class PredicateBasedNotifierDecoratorTest {
	@Test
	void shouldDelegateIfPredicateApplies() {
		AtomicBoolean notified = new AtomicBoolean(false);

		new PredicateBasedNotifierDecorator(context -> notified.set(true), context -> true).notify(new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>()));

		assertTrue(notified.get());
	}

	@Test
	void shouldNotDelegateIfPredicateDoesNotApplies() {
		AtomicBoolean notified = new AtomicBoolean(false);

		new PredicateBasedNotifierDecorator(context -> notified.set(true), context -> false).notify(new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>()));

		assertFalse(notified.get());
	}
}