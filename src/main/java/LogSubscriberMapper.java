import java.util.List;
import java.util.stream.Collectors;

public class LogSubscriberMapper {

	static public List<Subscriber> getRelevantSubscribers(String logMessage, List<Subscriber> allSubscribers) {
		return  allSubscribers.stream()
				.filter(s -> s.isRelevantLogMessage(logMessage))
				.collect(Collectors.toList());
	}

}
