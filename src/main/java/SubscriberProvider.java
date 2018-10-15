import java.util.ArrayList;
import java.util.List;

public class SubscriberProvider {
	
	public List<Subscriber> get() {
		List<Subscriber> subscribers = new ArrayList<>();
		subscribers.add(new Subscriber("Robert Glaser", LogType.EXCEPTION42));
		subscribers.add(new Subscriber("Britta Glatt", LogType.ERROR));
		subscribers.add(new Subscriber("Michael Grün", LogType.ERROR));
		subscribers.add(new Subscriber("Antonio Materazzo", LogType.ERROR));
		subscribers.add(new Subscriber("Fritz Schnitzel", LogType.ERROR));
		return subscribers;
	}
}
