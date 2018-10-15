package logwatcher;

import java.util.List;

public class CompositeNotifier implements Notifier {

	private final List<Notifier> notifiers;

	public CompositeNotifier(List<Notifier> notifiers) {
		this.notifiers = notifiers;
	}

	@Override
	public void notify(LogEntryWithContext context) {
		notifiers.forEach(notifier -> notifier.notify(context));
	}
}
