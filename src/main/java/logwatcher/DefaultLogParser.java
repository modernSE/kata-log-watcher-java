package logwatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultLogParser implements LogParser {

	private final Pattern EXCEPTION_CODE = Pattern.compile(".*[eE]xception [cC]ode (?<code>[0-9]+).*");

	@Override
	public LogEntry parse(String logMessage) {
		if (logMessage == null || logMessage.isEmpty()) {
			return new LogEntry(0, "", LogLevel.INFO);
		} else if (logMessage.matches(".*[eE]rror.*") || EXCEPTION_CODE.matcher(logMessage).matches()) {
			Matcher matcher = EXCEPTION_CODE.matcher(logMessage);
			if (matcher.matches()) {
				String code = matcher.group("code");
				return new LogEntry(Integer.parseInt(code), logMessage, LogLevel.ERROR);
			} else {
				return new LogEntry(0, logMessage, LogLevel.ERROR);
			}
		} else if (logMessage.matches(".*[dD]ebug.*")) {
			return new LogEntry(0, logMessage, LogLevel.DEBUG);
		} else if (logMessage.matches(".*[tT]race.*")) {
			return new LogEntry(0, logMessage, LogLevel.TRACE);
		} else {
			return new LogEntry(0, logMessage, LogLevel.INFO);
		}
	}
}
