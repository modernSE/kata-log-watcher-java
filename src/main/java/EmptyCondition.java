
public class EmptyCondition implements ILogCondition {

	@Override
	public boolean notify(String logMessage) {
		return this.isError(logMessage);
	}

	@Override
	public String formatMessage(String logMessage, String previousTrace) {
		return logMessage;
	}

}
