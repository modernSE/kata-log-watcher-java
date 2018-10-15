import java.util.Arrays;
import java.util.List;

public class ErrorGroup implements Group {
	private List<String> subscribers;
	
	public ErrorGroup(String... subscribers) {
		this.subscribers = Arrays.asList(subscribers);
	}

	@Override
	public List<String> getSubscribers() {
		return subscribers;
	}

	@Override
	public boolean checkLogMessage(String logMessage) {
		return "An error occured".equalsIgnoreCase(logMessage);
	}

}
