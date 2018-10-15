import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private final List<Subscriber> subscribers;
    private final MailClient mailClient;
	private Logger logger;
	private LogMessageHistory logMessageHistory;

    public LogWatcher(List<Subscriber> subscribers, Logger logger, MailClient mailClient,
    		LogMessageHistory logMessageHistory) {
    	this.subscribers = subscribers;
    	this.mailClient = mailClient;
    	this.logger = logger;
		this.logMessageHistory = logMessageHistory;
    }
    
    public void watchAndAlert() {
        Optional<String> logEntry = logger.popNextLine();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(String logMessage) {
    	logMessageHistory.addLogMessage(logMessage);
        for(final Subscriber subscriber : subscribers) {
        	if(!subscriber.shouldLog(logMessage))
        		continue;
        	
        	mailClient.sendMail(subscriber.getEmail(), logMessage);
        }
    }
}
