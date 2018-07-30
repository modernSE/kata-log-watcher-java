
public class EmailNotifier implements INotifier {

	private Util util;

	public EmailNotifier(Util util) {
		this.util = util;
	}
	
	@Override
	public void notifyXXX(User user, LogMessage logMessage) {
		if(user.getNotifiyOnTypes().contains(logMessage)) {
			util.writeEmail(new Mail(user.getEmail() , logMessage, getTrace(logMessage, user.getName())));
		}
	}
	
	private String getTrace(LogMessage logMessage, String name) {
		return "trace...";
	}

}
