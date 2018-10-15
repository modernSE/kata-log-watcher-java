import java.util.ArrayList;
import java.util.List;

public class SubscriberInitializer {
	
	
	 static List<Subscriber> getInitializedSubscribers () {
		List<Subscriber> subscribers;
		subscribers = new ArrayList<Subscriber>();
		
		List<String> robertGlaserLogMessages = new ArrayList<String>();
		robertGlaserLogMessages.add("ExceptionCode 42");
		
		List<String> standardLogMessages = new ArrayList<String>();
		standardLogMessages.add("An error occured");

		subscribers.add(new Subscriber("Robert Glaser", robertGlaserLogMessages));
		subscribers.add(new Subscriber("Britta Glatt", standardLogMessages));
		subscribers.add(new Subscriber("Michael Grün", standardLogMessages));
		subscribers.add(new Subscriber("Antonio Materazzo", standardLogMessages));
		subscribers.add(new Subscriber("Fritz Schnitzel", standardLogMessages));
		
		return subscribers;
		
	}

}
