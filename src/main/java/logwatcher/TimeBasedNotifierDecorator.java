package logwatcher;

import java.time.Clock;
import java.util.function.Predicate;

public final class TimeBasedNotifierDecorator implements Notifier {

	private final Clock clock;
	private final Notifier delegate;
	private final Predicate<Clock> timePredicate;

	public TimeBasedNotifierDecorator(Clock clock, Notifier delegate, Predicate<Clock> timePredicate) {
		this.clock = clock;
		this.delegate = delegate;
		this.timePredicate = timePredicate;
	}

	@Override
	public void notify(LogEntryWithContext context) {
		if (timePredicate.test(clock)) {
			delegate.notify(context);
		}
	}
}
