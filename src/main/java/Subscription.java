import java.util.Collections;
import java.util.Set;

public class Subscription {
	public static Subscription[] availableSubscriptions = createSubscriptions();
	
	public String name;
	public Set<Subscription> subSubscriptions = Collections.emptySet();
		
	public Subscription (final String name) {
		this.name = name;
	}
	
	private static Subscription[] createSubscriptions() {
		
		Subscription[] availableSubscriptions = new Subscription[3];
		
		//availableSubscriptions[0] = new Subscription("ALL");
		availableSubscriptions[0] = new Subscription("ExceptionCode 42");
		availableSubscriptions[1] = new Subscription("An error occured");
		availableSubscriptions[2] = new Subscription("LAST_STACK_TRACE");
		
		return availableSubscriptions;
	}
}
