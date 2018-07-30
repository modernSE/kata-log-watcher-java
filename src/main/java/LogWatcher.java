import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private final ISubscriber[] subscribers;
    private LogEntry lastTrace;
    
    public LogWatcher(final ISubscriber[] subscribers) {
    	this.subscribers = subscribers;
   
    }
    
    public void watchAndAlert() {
       LogEntry logEntry = Log.popNextLine();

       notifySubscribers(logEntry);
       
       if (logEntry.logLevel == LogLevel.Trace) {
    	   lastTrace = logEntry;
       }
    }

    private void notifySubscribers(LogEntry logEntry) {
        for (int i = 0; i < subscribers.length; i++) {
        	if (logEntry.logLevel >= subscribers[i].getMinLogLevel()) {
        		logEntry.message.ifPresent(subscribers[i]::writeMessage);
        		
            	if (logEntry.logLevel == LogLevel.Error42 && lastTrace != null) {            		
            		lastTrace.message.ifPresent(subscribers[i]::writeMessage);
            	}
        	} 	
        }
    }
}
