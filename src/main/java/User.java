import java.util.HashSet;
import java.util.Set;

public class User {
	String firstName;
	String lastName;
	
	String mail;
	
	Set<LogType> subscriptions = new HashSet<>();
	
	public static User fromString(String name) {
		String[] parts = name.split(" ");
		return new User(parts[0], parts[1]);
	}
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void addSubscription(LogType type) {
		subscriptions.add(type);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public String getMail() {
		NameMapper mapper = new EmailNameMapper();
		return mapper.map(firstName + " " + lastName);
	}

	public boolean hasSubscribed(LogType logType) {
		return subscriptions.contains(logType);
	}
	
}
