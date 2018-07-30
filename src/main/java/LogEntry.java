import java.util.Optional;

public class LogEntry {
	
	public Optional<String> message;
	public int logLevel;
	
	public LogEntry(Optional<String> message, int logLevel)
	{
		this.message = message;
		this.logLevel = logLevel;
	}
}
