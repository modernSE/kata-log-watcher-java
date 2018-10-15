package logwatcher;

import java.util.Date;
import java.util.Optional;

public class Log {

	public static Optional<String> popNextLine() {
		long someNumber = new Date().getTime();
		if (someNumber % 2 == 0) {
			return Optional.empty();
		} else if (someNumber % 3 == 0) {
			return Optional.of("An error occured");
		} else if (someNumber % 5 == 0) {
			return Optional.of("Debug output");
		} else if (someNumber % 7 == 0) {
			return Optional.of("Trace output");
		} else {
			return Optional.of("ExceptionCode 42");
		}

	}
}
