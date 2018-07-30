import java.util.Optional;

public class ErrorStateGetter {
	
	public Optional<LogMessage> getErrorMessage() {
		return Log.popNextLine();
	}
}
