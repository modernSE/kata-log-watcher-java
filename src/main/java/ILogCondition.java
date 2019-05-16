
public interface ILogCondition {

	public boolean notify(String logMessage);
	
	public String formatMessage(String logMessage, String previousTrace);
}
