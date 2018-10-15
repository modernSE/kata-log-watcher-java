
public class Code42Message implements LogMessage {
	private String message;
	
	public Code42Message(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
