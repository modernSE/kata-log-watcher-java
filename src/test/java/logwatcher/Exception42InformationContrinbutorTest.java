package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logwatcher.Exception42InformationContrinbutor;
import logwatcher.LogEntry;
import logwatcher.LogEntryWithContext;
import logwatcher.LogLevel;

class Exception42InformationContrinbutorTest {

	Exception42InformationContrinbutor informationContrinbutor;

	@BeforeEach
	void setup() {
		informationContrinbutor = new Exception42InformationContrinbutor();
	}


	@Test
	void shouldContributeInformationForErrorCode42IfTraceLogsInHistory() {
		LogEntryWithContext logEntryWithContext = new LogEntryWithContext(new LogEntry(42, "ERROR MESSAGE", LogLevel.ERROR), new ArrayDeque<>(List.of(new LogEntry(0, "TRACE MESSAGE", LogLevel.TRACE), new LogEntry(0, "TRACE MESSAGE 2", LogLevel.TRACE))));

		Optional<String> actual = informationContrinbutor.additionalInformation(logEntryWithContext);

		assertTrue(actual.isPresent());
		assertEquals("Previous Trace Level information: TRACE MESSAGE 2,TRACE MESSAGE", actual.get());
	}

	@Test
	void shouldNotContributeInformationForErrorCode42IfNoTraceLogsInHistory() {
		LogEntryWithContext logEntryWithContext = new LogEntryWithContext(new LogEntry(42, "ERROR MESSAGE", LogLevel.ERROR), new ArrayDeque<>());

		Optional<String> actual = informationContrinbutor.additionalInformation(logEntryWithContext);

		assertTrue(actual.isEmpty());
	}

}