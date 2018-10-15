import java.util.Optional;

public interface Logger {

	Optional<String> popNextLine();

	String getLastTraceOutput();

}