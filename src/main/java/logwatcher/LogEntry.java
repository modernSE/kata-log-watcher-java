package logwatcher;

public class LogEntry {

	private final int code;
	private final String text;
	private final LogLevel severity;

	public LogEntry(int code, String text, LogLevel severity) {
		this.code = code;
		this.text = text;
		this.severity = severity;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public LogLevel getSeverity() {
		return severity;
	}
}
