package logwatcher;

import java.util.Set;

public class SeverityFilterNotifierDecorator implements Notifier {

	private final Notifier delegate;
	private final Set<LogLevel> acceptedLevels;

	public SeverityFilterNotifierDecorator(Notifier delegate, Set<LogLevel> acceptedLevels) {
		this.delegate = delegate;
		this.acceptedLevels = acceptedLevels;
	}

	@Override
	public void notify(LogEntryWithContext context) {
		if (acceptedLevels.contains(context.getCurrent().getSeverity())) {
			delegate.notify(context);
		}
	}
}
