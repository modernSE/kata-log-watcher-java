import java.util.Optional;

import email.Notificator;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private final Notificator notificator;

    public LogWatcher(Notificator notificator) {
        this.notificator = notificator;
    }

    private static final String[] subscribers = {"Robert Glaser", "Britta Glatt", "Michael Grün"};

    public void watch() {
        alert(Log.popNextLine());
    }

    void alert(Optional<String> logEntry) {
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

            notificator.notify(name, logMessage);
        }
    }
}
