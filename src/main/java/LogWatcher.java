import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    //private static final String[] subscribers = {"Robert Glaser", "Britta Glatt", "Michael Grün"};

    private Log log;
    private Integer statusCodeFilter;

    public LogWatcher(Log log) {
        this.log = log;
    }

    private List<INotificationSubscriber> subscribers = new ArrayList<>();

    public void setStatusCodeFilter(Integer statusCodeFilter) {
        this.statusCodeFilter = statusCodeFilter;
    }

    public void addSubscriber(INotificationSubscriber sub) {
        subscribers.add(sub);
    }

    public void watchAndAlert() {
        Optional<Message> logEntry = log.popNextLine();
        if(logEntry.isPresent()) {
            Date date = new Date();
            int hours = date.getHours();
            if(statusCodeFilter == null
             || statusCodeFilter != null && logEntry.get().getStatusCode() == statusCodeFilter) {
                notifySubscribers(logEntry.get());
            } 
        }
    }
  //  name = name.toLowerCase();
   // name.replace("ü", "ue");
    //name.replace("ä", "ae");
    //name.replace("ö", "oe");
    //name.replace(" ", ".");
    //name = name + "@cas.de";
    private void notifySubscribers(Message logMessage) {
        for(var subscriber:subscribers) {
            subscriber.notify(logMessage);
        }
    }
}
