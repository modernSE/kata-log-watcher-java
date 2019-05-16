import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private static final String[] allSubscribers = {"Robert Glaser", "Britta Glatt", "Michael Grün", "Antonio Materazzo", "Fritz Schnitzel"};
    private static final String[] robertGlaser = {"Robert Glaser"};
    
    List<String> logEntries = new ArrayList<>();
        
    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        if (!logEntry.isPresent()) {
        	return;
        }
        logEntries.add(logEntry.get());
        if (logEntry.get().equals("An error occured")) {
        	notifySubscribers(logEntry.get(), allSubscribers);
        }
        if (logEntry.get().equals("ExceptionCode 42")) {
        	String lastTraceOutput = "";
        	for (String lastLogEntry : logEntries) {
        		if (lastLogEntry.equals("Trace output")) {
        			lastTraceOutput = lastLogEntry;
        		}
           	}
        	notifySubscribers(logEntry.get(), robertGlaser);
        	notifySubscribers(lastTraceOutput, robertGlaser);
        }
    }

    private void notifySubscribers(String logMessage, String [] subscribers) {
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
