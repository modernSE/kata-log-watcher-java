package logwatcher;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LogEntryWithContext {

	private final LogEntry current;
	private final Deque<LogEntry> history;

	public LogEntryWithContext(LogEntry current, Deque<LogEntry> history) {
		this.current = current;
		this.history = history;
	}

	public Deque<LogEntry> getHistory() {
		return history;
	}

	public List<LogEntry> getPreviousMessagesForLevel(LogLevel level) {
		List<LogEntry> previousEntriesOfLevel = new LinkedList<>();
		history.descendingIterator().forEachRemaining(entry -> {
			if (entry.getSeverity() == level) {
				previousEntriesOfLevel.add(entry);
			}
		});
		return previousEntriesOfLevel;
	}

	public LogEntry getCurrent() {
		return current;
	}
}
