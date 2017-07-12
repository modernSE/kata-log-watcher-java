import java.util.Set;

public class Subscriber {

	private String firstname;
	private String lastname;
	private String emailAdress;
    private Set<LogEntryType> subscribedLogTypes;
    
	public Subscriber(String firstname, String lastname, Set<LogEntryType> subscribedLogTypes) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailAdress = createEmailAdress();
		this.subscribedLogTypes = subscribedLogTypes;
	}
	
	public Set<LogEntryType> getSubscribedLogTypes() {
		return this.subscribedLogTypes;
	}
	
	public String getEmailAdress() {
		return this.emailAdress;
	}
	
	private String createEmailAdress() {
		return replaceSpecialCharacters(firstname).toLowerCase() + "." 
				+ replaceSpecialCharacters(lastname).toLowerCase() + "@cas.de";
	}
	
	private String replaceSpecialCharacters(String input) {
		input.replace("ü", "ue");
		input.replace("ä", "ae");
		input.replace("ö", "oe");		
		return input;
	}

}
