import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private String[] subscribers;
    LogEntryFilter filter;
    public LogWatcher(String[] subscribers, LogEntryFilter filter){
        this.subscribers = subscribers;
        this.filter = filter;
    }

    public Optional<String> watch() {
        return Log.popNextLine();
    }

    public boolean alert(Optional<String> logEntry) {
        if(filter.checkLogEntry(logEntry)){
            return SubscriberNotifier.notifySubscribers(logEntry.get(), subscribers);
        }

        return false;
    }

    
}
