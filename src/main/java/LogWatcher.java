import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

	//FIXME neuer subsciber -> test will fail
    private List<User> subscribers;
	private Util util;
	private ErrorStateGetter errorStateGetter;
	private List<INotifier> notifier;

    public LogWatcher(Util util, ErrorStateGetter errorStateGetter, List<User> subscribers, List<INotifier> notifier) {
		this.util = util;
		this.errorStateGetter = errorStateGetter;
		this.subscribers = subscribers;
		this.notifier = notifier;
	}
    
    public void watchAndAlert() {
    	Optional<LogMessage> logEntry = errorStateGetter.getErrorMessage();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(LogMessage logMessage) {
    	
    	for (int i = 0; i < subscribers.size(); i++) {
            User user = subscribers.get(i);
            notifier.forEach(n -> n.notifyXXX(user, logMessage));
        }
    }
}
