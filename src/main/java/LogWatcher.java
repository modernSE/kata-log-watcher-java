import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private static final String[] subscribers = {"Robert Glaser", "Britta Glatt", "Michael Grün"};

    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(String logMessage) {
        for (int i = 0; i < subscribers.length; i++) {
            String name = subscribers[i];
            name = name.toLowerCase();
            name.replace("ü", "ue");
            name.replace("ä", "ae");
            name.replace("ö", "oe");
            name.replace(" ", ".");
            name = name + "@cas.de";

            Util.writeEmail(name, logMessage);
        }
    }
}
