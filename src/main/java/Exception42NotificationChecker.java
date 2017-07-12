import java.util.Optional;

public class Exception42NotificationChecker extends NotificationChecker {

	@Override
	boolean check(Optional<String> logEntry){
		return LogType.EXCEPTION42==getLogType(logEntry);
	}
	
}
