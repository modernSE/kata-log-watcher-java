import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */


public class LogWatcher {
    private static final String ROBERT_GLASER = "Robert Glaser";
    private static final String ERROR = "An error occured";
    private static final String EXCEPTION_CODE_42 = "ExceptionCode 42";
    private static final String[] subscribers = {ROBERT_GLASER, "Britta Glatt", "Michael Grün", "Antonio Materazzo", "Fritz Schnitzel"};

    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
            logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(String logMessage) {
        for (int i = 0; i < subscribers.length; i++) {
            String name = subscribers[i];

            if (logMessage.equals(ERROR) || (name.equals(ROBERT_GLASER) && logMessage.equals(EXCEPTION_CODE_42))){
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
}
