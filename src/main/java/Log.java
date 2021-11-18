import java.util.Date;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log implements LogProvider {

    @Override
    public Optional<LogLine> popNextLine() {
        long someNumber = new Date().getTime();
        if (someNumber % 2 == 0) {
            return Optional.empty();
        } else if (someNumber % 3 == 0) {
            return Optional.of(new LogLine(LogLevel.ERROR, "An error occured"));
        } else if (someNumber % 5 == 0) {
            return Optional.of(new LogLine(LogLevel.DEBUG, "Debug output"));
        } else if (someNumber % 7 == 0) {
            return Optional.of(new LogLine(LogLevel.TRACE, "Trace output"));
        } else {
            return Optional.of(new LogLine(LogLevel.ERROR, "ExceptionCode 42"));
        }

    }
}
