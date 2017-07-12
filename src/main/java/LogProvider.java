import java.util.Optional;

public interface LogProvider {

	Optional<LogMessage> getLog();

	String getTrace();
	
}
