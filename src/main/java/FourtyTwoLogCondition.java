
public class FourtyTwoLogCondition implements ILogCondition {

	@Override
	public boolean notify(String logMessage) {
		return logMessage.equals("ExceptionCode 42");
	}

	@Override
	public String formatMessage(String logMessage, String previousTrace) {
		if (logMessage.equals("ExceptionCode 42"))
			return logMessage + ", Debug Trace: " + previousTrace;
		else 
			return logMessage;
	}

}
