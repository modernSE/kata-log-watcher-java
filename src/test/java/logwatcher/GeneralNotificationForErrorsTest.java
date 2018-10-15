package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

class GeneralNotificationForErrorsTest {

	@Test
	void shouldNotifyTeamForErrorLogs() {
		GeneralNotificationForErrors filter = new GeneralNotificationForErrors(Set.of("Example@foo.com"));

		Set<String> result = filter.recipientAddresses(new LogEntry(0, "Test", LogLevel.ERROR));

		assertEquals(Set.of("Example@foo.com"), result);
	}

	@Test
	void shouldNotNotifyTeamForNonErrorLogs() {
		GeneralNotificationForErrors filter = new GeneralNotificationForErrors(Set.of("Example@foo.com"));

		Set<String> result1 = filter.recipientAddresses(new LogEntry(0, "Test", LogLevel.INFO));
		Set<String> result2 = filter.recipientAddresses(new LogEntry(0, "Test", LogLevel.TRACE));
		Set<String> result3 = filter.recipientAddresses(new LogEntry(0, "Test", LogLevel.DEBUG));

		assertEquals(Set.of(), result1);
		assertEquals(Set.of(), result2);
		assertEquals(Set.of(), result3);
	}

}