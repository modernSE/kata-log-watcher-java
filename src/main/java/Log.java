import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    public static Optional<LogMessage> popNextLine() {
        long someNumber = new Date().getTime();
        if (someNumber % 2 == 0) {
            return Optional.empty();
        } else if (someNumber % 3 == 0) {
            return Optional.of(LogMessage.ERROR);
        } else if (someNumber % 5 == 0) {
            return Optional.of(LogMessage.DEBUG);
        } else if (someNumber % 7 == 0) {
            return Optional.of(LogMessage.TRACE);
        } else {
            return Optional.of(LogMessage.CODE42);
        }

    }
}
