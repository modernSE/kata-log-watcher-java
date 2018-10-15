package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

class Exception42MailRecipientProviderTest {


	@Test
	void shouldOnlySendToGivenReciepients() {
		Exception42MailRecipientProvider filter = new Exception42MailRecipientProvider(Set.of("Example"));

		Set<String> error10Recipients = filter.recipientAddresses(new LogEntry(10, "Text", LogLevel.ERROR));
		Set<String> error42Recipients = filter.recipientAddresses(new LogEntry(42, "Text", LogLevel.ERROR));

		assertEquals(Set.of(), error10Recipients);
		assertEquals(Set.of("Example"), error42Recipients);
	}

}