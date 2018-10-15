
public class DebugMessage implements LogMessage {
	private String message;
	
	public DebugMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
