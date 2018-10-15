package logwatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

class ChatNotifierTest {

	@Test
	void shouldNotifyChat() {
		AtomicReference<String> chosenChannel = new AtomicReference<>("Not Written");
		AtomicReference<String> writtenMessage = new AtomicReference<>("Not Written");

		ChatNotifier chatNotifier = new ChatNotifier("#foo", (channel,message) -> {
			chosenChannel.set(channel);
			writtenMessage.set(message);
		});

		chatNotifier.notify(new LogEntryWithContext(new LogEntry(0, "test", LogLevel.ERROR), new ArrayDeque<>()));

		assertEquals("#foo", chosenChannel.get());
		assertEquals("Sent message to channel '#foo': ERROR(0) test", writtenMessage.get());
	}

}