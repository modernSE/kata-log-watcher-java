import java.util.Optional;

import javax.print.DocFlavor.INPUT_STREAM;

public class LogEntryExtractor {
	
	public static LogEntryType getLogEntryType(Optional<String> input) {
		if(!input.isPresent()) {return LogEntryType.OK;}
		else {
			String string = input.get();
			switch (string) {
			case "An error occured":
				return LogEntryType.ERROR;
			case "Debug output":
				return LogEntryType.DEBUG;
			case "Trace output": 
				return LogEntryType.TRACE;
			case "ExceptionCode 42": 
				return LogEntryType.EXCEPTION42;
			default:
				return LogEntryType.OK;
			}
		}
	}

}
