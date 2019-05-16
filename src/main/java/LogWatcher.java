import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

	private Supplier<Optional<String>> logSupplier;
	private List<Subscriber> subscribers;

    public LogWatcher(List<Subscriber> subscribers, Supplier<Optional<String>> logSupplier) {
		this.subscribers = subscribers;
		this.logSupplier = logSupplier;		
    }
    
    public void watchAndAlert() {
        Optional<String> logEntry = logSupplier.get();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(String logMessage) {
        for (Subscriber subscriber: subscribers) {
        	subscriber.receive(logMessage);
        }
    }
}
