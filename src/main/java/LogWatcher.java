import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private static final String[] subscribers = {"Robert Glaser", "Britta Glatt", 
    		"Michael Grün", "Antonio Materazzo", "Fritz Schnitzel"};
    
    private List<User> users;

    private LogProvider logProvider;

    public LogWatcher(LogProvider logProvider) {
		this.logProvider = logProvider;
		users = Arrays.stream(subscribers).map(User::fromString).collect(Collectors.toList());
		users.forEach(user -> user.addSubscription(LogType.ERROR));
		users.get(0).addSubscription(LogType.EXCEPTION42);
		users.get(0).addSubscription(LogType.TRACE);
	}

	public void watchAndAlert() {
        Optional<LogMessage> logEntry = logProvider.getLog();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(LogMessage logMessage) {
    	
        for (User user : users) {
        	if(logMessage.getType() == LogType.ERROR)
        		if(user.hasSubscribed(logMessage.getType()))
        			Util.writeEmail(user.getMail(), logMessage.getMessage());
        		if(user.hasSubscribed(LogType.TRACE)) {
        			Util.writeEmail(user.getMail(), logProvider.getTrace());
        		}
        }
    }
}
