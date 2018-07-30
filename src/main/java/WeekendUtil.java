import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class WeekendUtil {
	
	private StringBuilder stackTrace = new StringBuilder();
	private Subscriber weekendSubscriber;
	
	public WeekendUtil(String name) {
		this.weekendSubscriber = new Subscriber(name, "An error occured", "Trace output", "ExceptionCode 42", "Debug output");
	}
	
	public void notify(Optional<String> line) {
		stackTrace.append(line.orElse(""));
		
		Date currentTime = new Date();
		if(currentTime.getDay() > 5) {
			return;
		}
		
		String logMessage = line.get();
		for (Subscriber subscriber : subscribers) {
            if(subscriber.isLineRelevant(logMessage)) {
            	String name = subscriber.getName();
            	name = name.toLowerCase();
            	name.replace("ü", "ue");
            	name.replace("ä", "ae");
            	name.replace("ö", "oe");
            	name.replace(" ", ".");
            	name = name + "@cas.de";
            	
            	if(subscriber.isStackTraceRelevant(logMessage)) {
            		logMessage += "; StackTrace:"+stackTrace.toString();
            		stackTrace = new StringBuilder();
            	}
            	writeEmail(name, logMessage);
            }
		}
	}
	
    private void writeEmail(final String address, final String msg) {
        System.out.println("WeekendUtil: " + address + ": " + msg);
    }
}
