package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

// Integration Test for LogWatcher
class LogWatcherTest {

	Multimap<String, String> sentMails;
	Multimap<String, String> sentChats;

	@BeforeEach
	void setup() {
		sentMails = ArrayListMultimap.create();
		sentChats = ArrayListMultimap.create();
	}

	private LogWatcher createLogWatcherForTesting(Clock clock) {
		return LogWatcherFactory.createWithCustomSendersAndClock(
				() ->"on.call.man@cas.de",
				() -> "@on_call_man",
				(recipient, mailBody) -> sentMails.put(recipient, mailBody),
				(channel, message) -> sentChats.put(channel, message),
				clock
		);
	}

	@Test
	void shouldOnlySendErrorNotifications() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 8, 12, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		logWatcher.onLogMessage("info message");
		logWatcher.onLogMessage("error message");
		logWatcher.onLogMessage("Exception Code 12 Error");
		logWatcher.onLogMessage("trace message");
		logWatcher.onLogMessage("debug message");

		String expectedMessage = "error message\n\nAdditional Information:\n";
		String expectedMessageErrorCode12 = "Exception Code 12 Error\n\nAdditional Information:\n";
		assertEquals(8, sentMails.entries().size());
		assertTrue(sentMails.containsEntry("fritz.schnitzel@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("michael.gruen@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("antonia.materazzo@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("britta.glatt@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("fritz.schnitzel@cas.de", expectedMessageErrorCode12));
		assertTrue(sentMails.containsEntry("michael.gruen@cas.de", expectedMessageErrorCode12));
		assertTrue(sentMails.containsEntry("antonia.materazzo@cas.de", expectedMessageErrorCode12));
		assertTrue(sentMails.containsEntry("britta.glatt@cas.de", expectedMessageErrorCode12));

		assertEquals(2, sentChats.entries().size());
		assertTrue(sentChats.containsEntry("#theChannel", "Sent message to channel '#theChannel': ERROR(0) error message"));
		assertTrue(sentChats.containsEntry("#theChannel", "Sent message to channel '#theChannel': ERROR(12) Exception Code 12 Error"));
	}

	@Test
	void shouldSendAlsoRobertGlaserError42MessageAndAlsoAttachWithAdditionalContext() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 8, 12, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		logWatcher.onLogMessage("trace message");
		logWatcher.onLogMessage("Exception Code 42 Error");

		System.out.println(sentMails);
		System.out.println(sentChats);

		String expectedMessage = "Exception Code 42 Error\n\nAdditional Information:\nPrevious Trace Level information: trace message";
		assertEquals(5, sentMails.entries().size());
		assertTrue(sentMails.containsEntry("fritz.schnitzel@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("michael.gruen@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("antonia.materazzo@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("britta.glatt@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("robert.glaser@cas.de", expectedMessage));

		assertEquals(1, sentChats.entries().size());
		assertTrue(sentChats.containsEntry("#theChannel", "Sent message to channel '#theChannel': ERROR(42) Exception Code 42 Error"));
	}

	@Test
	void shouldSendChatMessagesDuringWorkingHours() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 8, 10, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		logWatcher.onLogMessage("error message");

		String expectedMessage = "error message\n\nAdditional Information:\n";
		assertEquals(4, sentMails.entries().size());
		assertTrue(sentMails.containsEntry("fritz.schnitzel@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("michael.gruen@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("antonia.materazzo@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("britta.glatt@cas.de", expectedMessage));

		assertEquals(1, sentChats.entries().size());
		assertTrue(sentChats.containsEntry("#theChannel", "Sent message to channel '#theChannel': ERROR(0) error message"));
	}

	@Test
	void shouldNotSendChatMessagesOutsideWorkingHours() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 8, 6, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		logWatcher.onLogMessage("error message");

		String expectedMessage = "error message\n\nAdditional Information:\n";
		assertEquals(4, sentMails.entries().size());
		assertTrue(sentMails.containsEntry("fritz.schnitzel@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("michael.gruen@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("antonia.materazzo@cas.de", expectedMessage));
		assertTrue(sentMails.containsEntry("britta.glatt@cas.de", expectedMessage));

		assertTrue(sentChats.isEmpty());
	}

	@Test
	void shouldNotSendAnyNotificationOnWeekends() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 10, 12, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		logWatcher.onLogMessage("error message");

		assertTrue(sentMails.isEmpty());
		assertTrue(sentChats.isEmpty());
	}

	@Test
	void shouldSendSpecialMessagesToOnCallDeveloperOnWeekendsIfThresholdReached() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 9, 6, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		IntStream.range(0, 11).forEach(index -> {
			logWatcher.onLogMessage("error message " + index);
		});

		String expectedMessage = "At least 10 errors were recorded, check it as you are on call!";
		assertEquals(1, sentMails.entries().size());
		assertTrue(sentMails.containsEntry("on.call.man@cas.de", expectedMessage));

		assertEquals(1, sentChats.entries().size());
		assertTrue(sentChats.containsEntry("@on_call_man", expectedMessage));
	}

	@Test
	void shouldNotSendSpecialMessagesToOnCallDeveloperOnWeekendsIfThresholdNotReached() {
		Clock clock = Clock.fixed(LocalDateTime.of(2020, 5, 9, 6, 0).toInstant(ZoneOffset.ofHours(2)), ZoneId.of("Europe/Berlin"));
		LogWatcher logWatcher = createLogWatcherForTesting(clock);

		IntStream.range(0, 10).forEach(index -> {
			logWatcher.onLogMessage("error message " + index);
		});

		assertTrue(sentMails.isEmpty());
		assertTrue(sentChats.isEmpty());
	}


}
