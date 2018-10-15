import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {


    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        if (logEntry.isPresent()) {
        	notifySubscribers(logEntry.get(), SubscriberInitializer.getInitializedSubscribers());	
        }
    }

    private void notifySubscribers(String logMessage, final List<Subscriber> subscribers) {
        List<Subscriber> relevantSubscriber = LogSubscriberMapper.getRelevantSubscribers(logMessage, subscribers);
    	for (Subscriber subscriber : relevantSubscriber) {
            notifySuscriber(logMessage, subscriber);
        }
    }

	private void notifySuscriber(String logMessage, Subscriber subscriber) {
		String name = subscriber.getName();
		
		name = name.toLowerCase();
		name.replace("ü", "ue");
		name.replace("ä", "ae");
		name.replace("ö", "oe");
		name.replace(" ", ".");
		name = name + "@cas.de";

		Util.writeEmail(name, logMessage);
	}
}
