
public class LogMessageFactory {
	public LogMessage getInstance(String log) {
		switch(log) {
		case "An error occured":
			return new ErrorMessage(log);
			
		case "Debug output":
			return new DebugMessage(log);
			
		case "Trace output":
			return new TraceMessage(log);
			
		case "ExceptionCode 42":
			return new Code42Message(log);
			
		default:
			return null;
		}
	}
}
