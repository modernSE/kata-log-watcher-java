import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {
	
	/*
	 * Open Closed verletzt:
	 * - subscribers nicht erweiterbar
	 * 
	 * Nicht testbar:
	 * - Log nicht austauschbar
	 * - Keine Ergebnisrückgabe
	 */

    
    private Log log;
    private Map<String, ILogCondition> subscribers;
    private IEmailWriteObserver observer;
    
    private String previousTrace;
    
    public LogWatcher(Log log, IEmailWriteObserver observer) {
		this.log = log;
		this.observer = observer;
		this.subscribers = new HashMap<>();
	}

	public void watchAndAlert() {
        Optional<String> maybeLogEntry = log.popNextLine();
        maybeLogEntry.ifPresent(logEntry -> {
        	if (this.isTrace(logEntry)) 
        		previousTrace = logEntry;
        	
        	notifySubscribers(logEntry);
        });
    }
	
	private boolean isTrace(String logEntry) {
		return logEntry.equals("Trace Output");
	}
	
	public void addSubscriber(String subscriber, ILogCondition condition) {
		this.subscribers.put(subscriber, condition);
	}

    private void notifySubscribers(String logMessage) {
        for (Entry<String, ILogCondition> subscriber : subscribers.entrySet()) {
        	if(subscriber.getValue().notify(logMessage)) {
	            String name = subscriber.getKey().toLowerCase();
	            ILogCondition condition = subscriber.getValue();
	            name.replace("ü", "ue");
	            name.replace("ä", "ae");
	            name.replace("ö", "oe");
	            name.replace(" ", ".");
	            name = name + "@cas.de";
	            this.observer.notify(name, condition.formatMessage(logMessage, previousTrace));
        	}
        }
    }
}
