import java.util.Date;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Log {

    public static final String AN_ERROR_OCCURED = "An error occured";
    public static final String DEBUG_OUTPUT = "Debug output";

    private int status;

    public Log(int status) {
        this.status = status;
    }

    public Optional<Message> popNextLine() {
        if (status == 0) {
            return Optional.empty();
        } else if (status == 1) {
            return Optional.of(new Message(AN_ERROR_OCCURED, 1));
        } else if (status == 2) {
            return Optional.of(new Message( DEBUG_OUTPUT,2));
        } else if (status == 3) {
            return Optional.of(new Message("Trace output",3));
        } else {
            return Optional.of(new Message("ExceptionCode 42","A stack trace", 42));
        }

    }
}
