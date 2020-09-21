import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private List<Subscriber> subscribers;
    private BiConsumer<String, String> notifyConsumer;

    public LogWatcher(List<Subscriber> subscribers, BiConsumer<String, String> notifyConsumer) {
        this.subscribers = subscribers;
        this.notifyConsumer = notifyConsumer;
    }    

    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(String logMessage) {
        for (Subscriber subscriber : subscribers) {
            notifyConsumer.accept(subscriber.getEmail(), logMessage);
        }
    }
}
