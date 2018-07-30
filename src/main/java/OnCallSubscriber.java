import java.util.ArrayList;

public class OnCallSubscriber implements ISubscriber{

	ISubscriber onCallDev;
	ArrayList<String> messages = new ArrayList<String>(10);
	
	OnCallSubscriber(ISubscriber onCallDev) {
		this.onCallDev = onCallDev;
	}
	
	@Override
	public int getMinLogLevel() {
		return LogLevel.Error;
	}

	@Override
	public void writeMessage(String msg) {
		if (isWeekend()) {
			messages.add(msg);
			
			if (messages.size() >= 10) {
				onCallDev.writeMessage("HELP!! MORE THAN 10 ERRORS HAVE HAPPENED!");
				messages.clear();
			}			
		} else {
			messages.clear();
		}
	}

	private boolean isWeekend() {
		return true;
	}
	
}
