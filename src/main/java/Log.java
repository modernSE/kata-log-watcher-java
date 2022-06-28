import java.util.Date;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    static String ERROR = "An error occured";
    static String DEBUG = "Debug output";
    static String TRACE = "Trace output";
    static String EXCEPTION_42 = "ExceptionCode 42";

    public static Optional<String> popNextLine() {
        long someNumber = new Date().getTime();
        if (someNumber % 2 == 0) {
            return Optional.empty();
        } else if (someNumber % 3 == 0) {
            return Optional.of(ERROR);
        } else if (someNumber % 5 == 0) {
            return Optional.of(DEBUG);
        } else if (someNumber % 7 == 0) {
            return Optional.of(TRACE);
        } else {
            return Optional.of(EXCEPTION_42);
        }
    }
}
