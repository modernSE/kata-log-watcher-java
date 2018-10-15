package logwatcher;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import logwatcher.DefaultLogParser;
import logwatcher.LogEntry;
import logwatcher.LogLevel;

class DefaultLogParserTest {

	DefaultLogParser parser;

	@BeforeEach
	void setup() {
		parser = new DefaultLogParser();
	}

	@ParameterizedTest(name = "Input: {0}")
	@MethodSource("exampleLogEntries")
	void shouldProduceCorrectLogEntries(String logMessage, Predicate<LogEntry> validator) {
		LogEntry logEntry = parser.parse(logMessage);
		assertTrue(() -> validator.test(logEntry));
	}

	static Stream<Arguments> exampleLogEntries() {
		return Stream.of(
				arguments("Example error message", logEntryPredicate(LogLevel.ERROR, "Example error message")),
				arguments("Example error message with Exception Code 12", logEntryPredicate(LogLevel.ERROR, "Example error message with Exception Code 12", 12)),
				arguments("Example trace message", logEntryPredicate(LogLevel.TRACE, "Example trace message")),
				arguments("Example debug message", logEntryPredicate(LogLevel.DEBUG, "Example debug message")),
				arguments("Example message", logEntryPredicate(LogLevel.INFO, "Example message")),
				arguments("Example info message", logEntryPredicate(LogLevel.INFO, "Example info message")),
				arguments("", logEntryPredicate(LogLevel.INFO, "")),
				arguments(null, logEntryPredicate(LogLevel.INFO, "")),
				arguments("Exception Code 12", logEntryPredicate(LogLevel.ERROR, "Exception Code 12", 12))
		);
	}

	private static Predicate<LogEntry> logEntryPredicate(LogLevel level, String message) {
		return logEntryPredicate(level, message, 0);
	}

	private static Predicate<LogEntry> logEntryPredicate(LogLevel level, String message, int code) {
		return entry -> entry.getSeverity() == level && message.equals(entry.getText()) && entry.getCode() == code;
	}

}