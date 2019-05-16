import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class Subscriber {

	private String name;
		
	Map<Predicate<String>, CommunicationChannel> conditionToChannel = new HashMap<>();
	
	public Subscriber(String name, Map<Predicate<String>, CommunicationChannel> conditionToChannel) {
		this.name = name;
		this.conditionToChannel = conditionToChannel;
	}
	
	public static Subscriber createSingleChannel(String name, Predicate<String> p, CommunicationChannel channel) {
		Map<Predicate<String>, CommunicationChannel> conditionToChannel = new HashMap<>();
		conditionToChannel.put(p, channel);
		return new Subscriber(name, conditionToChannel);
	}
	
	public void addChannel(Predicate<String> p, CommunicationChannel communicationChannel) {
		
	}
	
	public void receive(String message) {
		for (Entry<Predicate<String>, CommunicationChannel> entry : conditionToChannel.entrySet()) {
			Predicate<String> condition = entry.getKey();
			if (condition.test(message)) {
				CommunicationChannel channel = entry.getValue();
				channel.write(name, message);
			}
		}
	}
}
