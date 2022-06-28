import java.util.Date;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    public static final String DEBUG = "Debug output";

    public static final String TRACE = "Trace output";

    public static final String EXCEPTION = "ExceptionCode 42";

    public static final String ERROR = "An error occured";

    public static Optional<String> popNextLine() {
        long someNumber = new Date().getTime();
        if (someNumber % 2 == 0) {
            return Optional.empty();
        } 
        if (someNumber % 3 == 0) {
            return Optional.of(ERROR);
        } 
        if (someNumber % 5 == 0) {
            return Optional.of(DEBUG);
        }
        if (someNumber % 7 == 0) {
            return Optional.of(TRACE);
        }
        return Optional.of(EXCEPTION);       
    }
}
