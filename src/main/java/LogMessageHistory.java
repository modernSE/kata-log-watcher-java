import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogMessageHistory {

	private List<LogMessage> logMessageHistory = new ArrayList<>();

	public void addLogMessage(String messageType) {
		logMessageHistory.add(new LogMessage(messageType, Instant.now()));
		if (logMessageHistory.size() > 20) {
			logMessageHistory.remove(0);
		}
	}

	public List<String> getHistory(String messageType, Instant timestamp) {
		return logMessageHistory.stream().filter(logMessage -> logMessage.getMessageType().equals(messageType))
				.filter(logMessage -> logMessage.getTimestamp().isBefore(timestamp)).map(LogMessage::getMessageType)
				.collect(Collectors.toList());
	}
}
