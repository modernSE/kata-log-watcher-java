
public class LogMessage {
	
	String message;
	private LogType type;
	
	public LogMessage(String message) {
		this.message = message;
		if(message.equals("An error occured")) {
			this.type = LogType.ERROR;
		} else if(message.equals("ExceptionCode 42")) {
			this.type = LogType.EXCEPTION42;
		}
	}

	public LogType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
	
	
}
