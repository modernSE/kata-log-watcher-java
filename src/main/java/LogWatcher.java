import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private ArrayList<Subscriber> subscribers =  new ArrayList<>();

    public  static final Subscriber[] defaultSubscribers = { new EmailSubscriber("Robert Glaser"), new EmailSubscriber("Britta Glatt"),
            new EmailSubscriber("Michael Grün") };

    public void watchAndAlert(long someNumber) {
        Optional<String> logEntry = Log.popNextLine(someNumber);
        logEntry.ifPresent(this::notifySubscribers);
    }

    public void addSubriber(Subscriber subscriber) {
        this.subscribers.add( subscriber);
    }

    private void notifySubscribers(String logMessage) {
        for (int i = 0; i < subscribers.size(); i++) {
            String name = subscribers.get(i).getName();
            name = name.toLowerCase();
            name.replace("ü", "ue");
            name.replace("ä", "ae");
            name.replace("ö", "oe");
            name.replace(" ", ".");
            name = name + "@cas.de";

            subscribers.get(i).onNotify(name, logMessage);
           // Util.writeEmail(name, logMessage);
        }
    }
}
