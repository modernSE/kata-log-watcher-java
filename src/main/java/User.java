import java.util.Collections;
import java.util.Optional;

public class User {
	public String firstname;
	public String lastname;
	
	public Subscription[] subscriptions;
	
	public User(String firstname, String lastname, Subscription... subscriptions) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.subscriptions = subscriptions;		
	}
	
	public static final User[] subscribers = initUsers();

	private static User[] initUsers() {
		User[] users = new User[5];
		Subscription robertSpecial = new Subscription("ExceptionCode 42");
		robertSpecial.subSubscriptions = Collections.singleton(new Subscription("LAST_STACK_TRACE"));
		users[0] = new User("Robert", "Glaser", robertSpecial);
		users[1] = new User("Britta", "Glatt", new Subscription("ExceptionCode 42"), new Subscription("An error occured"));
		users[2] = new User("Michael", "Grün", new Subscription("ExceptionCode 42"), new Subscription("An error occured"));
		users[3] = new User("Antonio", "Materazzo", new Subscription("ExceptionCode 42"), new Subscription("An error occured"));
		users[4] = new User("Fritz", "Schnitzel", new Subscription("ExceptionCode 42"), new Subscription("An error occured"));
		
		users[5] = new User("Chat", "Bot", 
				new ChatSubscription("ExceptionCode 42"), 
				new ChatSubscription("Trace output"),
				new ChatSubscription("An error occured"));
		
		return users;
	}

   
}
