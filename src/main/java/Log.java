import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    public static final String EXCEPTION_CODE_42 = "ExceptionCode 42";
    public static final String TRACE_OUTPUT = "Trace output";
    public static final String DEBUG_OUTPUT = "Debug output";
    public static final String AN_ERROR_OCCURED = "An error occured";

	public static Optional<String> popNextLine() {
        long someNumber = new Date().getTime();
        if (someNumber % 2 == 0) {
            return Optional.empty();
        } else if (someNumber % 3 == 0) {
            return Optional.of(AN_ERROR_OCCURED);
        } else if (someNumber % 5 == 0) {
            return Optional.of(DEBUG_OUTPUT);
        } else if (someNumber % 7 == 0) {
            return Optional.of(TRACE_OUTPUT);
        } else {
            return Optional.of(EXCEPTION_CODE_42);
        }

    }
}
