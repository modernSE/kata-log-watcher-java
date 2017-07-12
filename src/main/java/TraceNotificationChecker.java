import java.util.Optional;

public class TraceNotificationChecker extends NotificationChecker {

	@Override
	boolean check(Optional<String> logEntry){
		return LogType.TRACE==getLogType(logEntry);
	}
	
}
