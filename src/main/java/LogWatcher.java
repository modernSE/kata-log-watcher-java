import java.util.Arrays;
import java.util.List;
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
        for (String subscriber : subscribers) {
            String name = subscriber;
            name = buildEmailAddress(name);

            Util.writeEmail(name, logMessage);
        }
    }

    private String buildEmailAddress(String name) {
        name = name.toLowerCase();
        name.replace("ü", "ue");
        name.replace("ä", "ae");
        name.replace("ö", "oe");
        name.replace(" ", ".");
        name = name + "@cas.de";
        return name;
    }
}
