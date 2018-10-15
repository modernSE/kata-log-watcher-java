package logwatcher;

import java.util.function.BiConsumer;

public class ChatNotifier implements Notifier {

	private final String channel;
	private final BiConsumer<String, String> messageSender;

	public ChatNotifier(String channel, BiConsumer<String, String> messageSender) {
		this.channel = channel;
		this.messageSender = messageSender;
	}

	@Override
	public void notify(LogEntryWithContext context) {
		messageSender.accept(channel, String.format("Sent message to channel '%s': %s(%s) %s", channel, context.getCurrent().getSeverity(), context.getCurrent().getCode(), context.getCurrent().getText()));
	}
}
