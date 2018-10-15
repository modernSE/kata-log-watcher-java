package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import logwatcher.MailNotifier.MailSender;

class MailNotifierTest {

	@Test
	void shouldSendMailToCorrectRecipients() {
		MailSenderFake mailSender = new MailSenderFake();

		MailNotifier mailNotifier = new MailNotifier(Set.of(entry -> Set.of("Test Person")), Set.of(entry -> Optional.of("Additional Info!!!")), mailSender);

		mailNotifier.notify(new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>()));

		assertEquals("test.person@cas.de", mailSender.recipient);
		assertEquals("test\n\nAdditional Information:\nAdditional Info!!!", mailSender.mailBody);
	}

	private static class MailSenderFake implements MailSender {

		String recipient;
		String mailBody;

		@Override
		public void send(String recipient, String mailBody) {
			this.recipient = recipient;
			this.mailBody = mailBody;
		}

	}

}