import java.util.Arrays;
import java.util.List;

public class Code42Group implements Group {
	private List<String> subscribers;
	
	public Code42Group(String... subscribers) {
		this.subscribers = Arrays.asList(subscribers);
	}

	@Override
	public List<String> getSubscribers() {
		return subscribers;
	}

	@Override
	public boolean checkLogMessage(String logMessage) {
		return "ExceptionCode 42".equalsIgnoreCase(logMessage);
	}

}
