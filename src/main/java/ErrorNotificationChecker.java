import java.util.Optional;

public class ErrorNotificationChecker extends NotificationChecker {

	@Override
	boolean check(Optional<String> logEntry){
		return LogType.ERROR==getLogType(logEntry);
	}
	
}
