package logwatcher;

import java.util.LinkedList;
import java.util.List;

public class OnThresholdReachedNotifier implements Notifier {

	private final int threshold;
	private final Runnable onThresholdReachedAction;
	private List<LogEntryWithContext> entries;

	public OnThresholdReachedNotifier(int threshold, Runnable onThresholdReachedAction) {
		this.threshold = threshold;
		this.onThresholdReachedAction = onThresholdReachedAction;
		entries = new LinkedList<>();
	}

	@Override
	public void notify(LogEntryWithContext context) {
		entries.add(context);
		if (entries.size() > threshold) {
			onThresholdReachedAction.run();
			entries.clear();
		}
	}

}
