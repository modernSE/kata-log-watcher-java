
public class GroupChatNotifier implements INotifier {

	
	private WorkingHoursUtil workingHoursUtil;

	public GroupChatNotifier(WorkingHoursUtil workingHoursUtil) {
		this.workingHoursUtil = workingHoursUtil;
	}
	
	@Override
	public void notifyXXX(User user, LogMessage logMessage) {
		if(workingHoursUtil.isWorkingHour() && logMessage != LogMessage.DEBUG) {
			System.out.println(logMessage);
		}
	}
}
