import java.sql.Timestamp;
import java.time.Instant;

public class LogMessage {
	
	private final String messageType;
	private final Instant timestamp;

	public LogMessage(String messageType, Instant timestamp) {
		this.messageType = messageType;
		this.timestamp = timestamp;
	}

	public String getMessageType() {
		return messageType;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}
