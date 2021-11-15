import java.util.Optional;

public interface LogProvider {
    Optional<LogLine> popNextLine();
}
