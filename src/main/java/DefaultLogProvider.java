import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultLogProvider implements LogProvider {
	
	List<LogMessage> trace = new ArrayList<>();

	@Override
	public Optional<LogMessage> getLog() {
		Optional<LogMessage> message = Log.popNextLine().map(LogMessage::new);
		message.ifPresent(trace::add);
		return message;
	}

	public String getTrace() {
		String result = "";
		for(LogMessage message : trace) {
			result += message.getMessage();
		}
		return result;
	}
	
	

}
