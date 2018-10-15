import java.util.List;

public class Subscriber {

	private String email;
	private List<String> notifyOn;

	public Subscriber(String name, List<String> notifyOn, boolean shouldSendTrace) {
		email = parseEmail(name);
		this.notifyOn = notifyOn;
	}
	
	public Subscriber(String name) {
		email = parseEmail(name);
	}
	
	private String parseEmail(final String name) {
		String parsedName = name;
		parsedName = name.toLowerCase();
		parsedName.replace("ü", "ue");
		parsedName.replace("ä", "ae");
		parsedName.replace("ö", "oe");
		parsedName.replace(" ", ".");
		parsedName = parsedName + "@cas.de";
		
		return parsedName;
	}
	
	public String getEmail() {
		return email;
	}

	public List<String> getNotifyOn() {
		return notifyOn;
	}
	
	public boolean shouldLog(final String logBody) {
		if(notifyOn == null || notifyOn.isEmpty())
			return true;
		
		return notifyOn.stream().anyMatch(notif -> logBody.contains(notif));
	}
	
	public boolean shouldSendTrace() {
		
	}
}
