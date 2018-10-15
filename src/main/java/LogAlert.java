import java.util.List;
import java.util.Optional;

public class LogAlert {
	
	private DefaultLogWatcher logWatcher;
	private SubscriptionNotifier subscriptionNotifier;

	public LogAlert(DefaultLogWatcher logWatcher, SubscriptionNotifier subscriptionNotifier) {
		this.logWatcher = logWatcher;
		this.subscriptionNotifier = subscriptionNotifier;
	}
	
	public void alertSubscribersForLogMessages() {
		List<String> logs = logWatcher.watch();
		String lastLog = logs.get(logs.size() - 1);
		 if (lastLog.equals(LogType.EXCEPTION42)) {
			 String lastTrace = lastLog.
		 }
		if(logType.isPresent()) {
			subscriptionNotifier.notifySubscribers(logType.get(), Util::writeEmail);
		}
	}
}
