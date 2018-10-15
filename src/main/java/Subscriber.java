import java.util.Arrays;
import java.util.List;

public class Subscriber {
	
	private final String name;
	private final List<LogType> relevantEvents;
	private final String email;

	public Subscriber(String name, LogType... relevantEvents) {
		this.name = name;
		this.relevantEvents = Arrays.asList(relevantEvents);
		this.email = convertNameToEmail();
	}

	public String getName() {
		return name;
	}

	public List<LogType> getRelevantEvents() {
		return relevantEvents;
	}
	
	public String getEmail() {
		return email;
	}

	private String convertNameToEmail() {
		String name = getName();
		name = name.toLowerCase();
		name.replace("ü", "ue");
		name.replace("ä", "ae");
		name.replace("ö", "oe");
		name.replace(" ", ".");
		name = name + "@cas.de";
		return name;
	}
}
