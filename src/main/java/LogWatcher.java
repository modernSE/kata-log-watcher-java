import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.Lists;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private Set<Subscriber> subscribers = new HashSet<>(); 
    private List<String> lastTraceEntries = Lists.newArrayList();
    
    public void addSubscriber (Subscriber newSubscriber) {
    	subscribers.add(newSubscriber);
    }
    
    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        LogEntryType logEntryType = LogEntryExtractor.getLogEntryType(logEntry);
        if(logEntryType.equals(LogEntryType.TRACE)) {
        	lastTraceEntry = logEntry.get();
        }
        if(logEntry.isPresent()) {
        	notifySubscribers(logEntry.get(), logEntryType);
        }
    }

    private void notifySubscribers(String logMessage, LogEntryType logEntryType) {
    	for (Subscriber subscriber : subscribers) {
    		if(subscriber.getSubscribedLogTypes().contains(logEntryType)) {
    			Util.writeEmail(subscriber.getEmailAdress(), logMessage);
    		}
		}
    }
}
