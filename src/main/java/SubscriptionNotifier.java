import java.util.function.BiConsumer;

public class SubscriptionNotifier {
	
	private SubscriberProvider subscriberProvider;

	public SubscriptionNotifier(SubscriberProvider subscriberProvider) {
		this.subscriberProvider = subscriberProvider;
	}
	
	public void notifySubscribers(LogType logType, BiConsumer<String, String> logMessageConsumer) {
		subscriberProvider.get().stream().filter(subscriber -> subscriber.getRelevantEvents().contains(logType))
				.forEach(subscriber -> logMessageConsumer.accept(subscriber.getEmail(), logType.name()));
	}
}
