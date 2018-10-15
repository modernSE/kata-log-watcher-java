
public class TraceMessage implements LogMessage {
	private String message;
	
	public TraceMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
