import java.util.Date;

public class ChatBotSubscriber implements ISubscriber{

	@Override
	public int getMinLogLevel() {
		return LogLevel.Trace;
	}

	@Override
	public void writeMessage(String msg) {
		int hour = 9;
		if (hour >= 8 && hour < 17) {
			System.out.println("GroupChat: " + msg);
        }
	}

}
