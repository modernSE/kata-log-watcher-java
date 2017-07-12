import java.util.Optional;

public abstract class NotificationChecker {
	
    protected enum LogType{NONE, ERROR, DEBUG, TRACE, EXCEPTION42};
	
    protected LogType getLogType(Optional<String> logEntry) {
    	LogType logType = LogType.NONE;
    	if(logEntry.isPresent()) {
    		String logEntryString = logEntry.get();
    		if("An error occured".equals(logEntryString)) {
    			logType = LogType.ERROR;
    		} else if("Debug output".equals(logEntryString)) {
    			logType = LogType.DEBUG;
    		} else if("Trace output".equals(logEntryString)) {
    			logType = LogType.TRACE;
    		}
    		if("ExceptionCode 42".equals(logEntryString)) {
    			logType = LogType.EXCEPTION42;
    		}
    	}
    	
    	return logType;
    }
	
	
	abstract boolean check(Optional<String> logEntry);
	
}
