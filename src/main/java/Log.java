import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    public static LogEntry popNextLine() {
        long someNumber = new Date().getTime();
        if (someNumber % 2 == 0) {
            return new LogEntry(Optional.empty(), LogLevel.None);
        } else if (someNumber % 3 == 0) {
        	return new LogEntry(Optional.of("An error occured"), LogLevel.Error);
        } else if (someNumber % 5 == 0) {
        	return new LogEntry(Optional.of("Debug output"), LogLevel.Debug);
        } else if (someNumber % 7 == 0) {
        	return new LogEntry(Optional.of("Trace output"), LogLevel.Trace);
        } else {
        	return new LogEntry(Optional.of("ExceptionCode 42"), LogLevel.Error42);
        }

    }
}
