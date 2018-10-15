import java.util.List;

public class Subscriber {
	
	List<String> relevantLogMessages;
	String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subscriber(String name, List<String> relevantLogMessages) {
		this.name= name;
		this.relevantLogMessages = relevantLogMessages;
	}
	
	public boolean isRelevantLogMessage(String logMessage) {
		return relevantLogMessages.contains(logMessage);
	}

	public void addRelevantLogMessage(String logMessage) {
		relevantLogMessages.add(logMessage);
	}
	
	public void removeRelevantLogMessage(String logMessage) {
		if(relevantLogMessages.contains(logMessage)) {
			relevantLogMessages.remove(logMessage);
		}
	}
}
