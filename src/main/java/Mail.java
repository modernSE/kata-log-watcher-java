
public class Mail {
	private String name;
	private String logMessage;
	
	public Mail(String name, String logMessage) {
		this.name = name;
		this.logMessage = logMessage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogMessage() {
		return logMessage;
	}
	
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
}
