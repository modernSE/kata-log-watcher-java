import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultLogger implements Logger {

	private List<LogMessage> logMessageHistory = new ArrayList<>();

	public Optional<String> popNextLine() {
		Optional<String> popNextLine = Log.popNextLine();
		if (popNextLine.isPresent()) {
			logMessageHistory.add(new LogMessage(popNextLine.get(), Instant.now()));
		}
		if(logMessageHistory.size() > 20) {
			logMessageHistory.remove(0);
		}
		return popNextLine;
	}
}
