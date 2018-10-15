package logwatcher;

import java.util.Deque;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LogWatcher {

	private final LogParser parser;
	private final Set<Notifier> notifiers;
	private final Deque<LogEntry> history;

	public LogWatcher(LogParser parser, Set<Notifier> notifiers) {
		this.parser = parser;
		this.notifiers = notifiers;
		history = new ConcurrentLinkedDeque<>();
	}

	public void onLogMessage(String message) {
		LogEntry logEntry = parser.parse(message);
		LogEntryWithContext logEntryWithContext = mapToContext(logEntry);
		processWithContext(logEntryWithContext);
	}

	private LogEntryWithContext mapToContext(LogEntry entry) {
		return new LogEntryWithContext(entry, history);
	}

	private void processWithContext(LogEntryWithContext context) {
		notifiers.forEach(notifier -> notifier.notify(context));
		history.addLast(context.getCurrent());
	}
}
