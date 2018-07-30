import java.util.List;

public class User {
	
	private String name;
	private List<LogMessage> notifiyOnTypes;
	
	
	public User(String name, List<LogMessage> notifiyOnTypes) {
		this.name = name;
		this.notifiyOnTypes = notifiyOnTypes;
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
    	String email = name.toLowerCase();
    	email.replace("ü", "ue");
    	email.replace("ä", "ae");
    	email.replace("ö", "oe");
    	email.replace(" ", ".");
    	email = name + "@cas.de";
		return email;
	}
	public List<LogMessage> getNotifiyOnTypes() {
		return notifiyOnTypes;
	}
	
	

}
