
public class Mail {

	private String address;
	private LogMessage msg;
	private String additionalMessage;

	public Mail(String adr, LogMessage message, String additionalMessage) {
		address = adr;
		msg = message;
		this.additionalMessage = additionalMessage;
	}

	public String getAddress() {
		return address;
	}

	public LogMessage getCode() {
		return msg;
	}
	
	public String getMsg() {
		return map(msg);
	}
	
	public String getAdditionalMessage() {
		return additionalMessage;
	}

	private String map(LogMessage msg2) {
		switch (msg2) {
		case ERROR:
			return "An error occured";
		case DEBUG:
			return "Debug output";
		case CODE42:
			return "ExceptionCode 42";
		case TRACE:
			return "Trace output";
		default:
			break;
		}
		
		return null;
	}
}