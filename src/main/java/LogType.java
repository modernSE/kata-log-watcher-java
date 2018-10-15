import java.util.Arrays;
import java.util.Optional;

public enum LogType {
	ERROR("An error occured"), DEBUG("Debug output"), TRACE("Trace output"), EXCEPTION42("ExceptionCode 42");

	private String name;

	LogType(String name) {
		this.name = name;
	}

	public static Optional<LogType> from(String name) {
		return Arrays.stream(LogType.values()).filter(logType -> logType.name.equals(name)).findFirst();
	}
}
