import java.util.Date;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    public static Optional<LogMessage> popNextLine(long time) {
        long someNumber = time;
        if (someNumber % 2 == 0) {
            return Optional.empty();
        } else if (someNumber % 3 == 0) {
            return Optional.of(new LogMessage("An error occured", 1));
        } else if (someNumber % 5 == 0) {
            return Optional.of(new LogMessage("Debug output", 3));
        } else if (someNumber % 7 == 0) {
            return Optional.of(new LogMessage("Trace output", 4));
        } else {
            return Optional.of(new LogMessage("ExceptionCode 42", 2));
        }

    }
}
