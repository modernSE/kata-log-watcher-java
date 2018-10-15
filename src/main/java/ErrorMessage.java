
public class ErrorMessage implements LogMessage {
	private String message;
	
	public ErrorMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
