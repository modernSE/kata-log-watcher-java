import java.util.Optional;

public class DebugNotificationChecker extends NotificationChecker {

	@Override
	boolean check(Optional<String> logEntry){
		return LogType.DEBUG==getLogType(logEntry);
	}
	
}
