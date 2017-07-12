import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private static final String[] errorSubscribers = {"Robert Glaser", "Britta Glatt", "Michael Grün", "Antonio Materazzo", "Fritz Schnitzel"};
    private static final String[] developers = {"Michael Grün", "Antonio Materazzo", "Fritz Schnitzel"};
    private static final String[] robertGlaser = {"Robert Glaser"};
    
    //TODO: inject list of notificationcheckers
    
    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        ErrorNotificationChecker errorNotificationChecker = new ErrorNotificationChecker();
        if(errorNotificationChecker.check(logEntry)) {
        	notifySubscribers(logEntry.get(), errorSubscribers);
        }
        Exception42NotificationChecker exception42NotificationChecker = new Exception42NotificationChecker();
        if(exception42NotificationChecker.check(logEntry)) {
        	notifySubscribers(logEntry.get(), robertGlaser);
        }
    }

    private void notifySubscribers(String logMessage, String[] notifiedPeople) {
        for (int i = 0; i < notifiedPeople.length; i++) {
            String name = notifiedPeople[i];
            name = getMailAdress(name);

            Util.writeEmail(name, logMessage);
        }
    }

	private String getMailAdress(String name) {
		name = name.toLowerCase();
		name.replace("ü", "ue");
		name.replace("ä", "ae");
		name.replace("ö", "oe");
		name.replace(" ", ".");
		name = name + "@cas.de";
		return name;
	}
	
	
}
