package logwatcher;

import java.util.function.Predicate;

public class PredicateBasedNotifierDecorator implements Notifier {

	private final Notifier delegate;
	private final Predicate<LogEntryWithContext> contextPredicate;

	public PredicateBasedNotifierDecorator(Notifier delegate, Predicate<LogEntryWithContext> contextPredicate) {
		this.delegate = delegate;
		this.contextPredicate = contextPredicate;
	}

	@Override
	public void notify(LogEntryWithContext context) {
		if (contextPredicate.test(context)) {
			delegate.notify(context);
		}
	}
}
