package logwatcher;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MainApplication {

	public static void main(String[] args) {
		Supplier<String> developerOnCall = () -> "Robert Glaser";
		Supplier<String> developerOnCallChatUsername = () -> "@robert.glaser";

		var logWatcher = LogWatcherFactory.createDefaultConfiguration(developerOnCall, developerOnCallChatUsername);

		IntStream.range(0, 100).forEach(index -> {
			Log.popNextLine().ifPresent(logWatcher::onLogMessage);
		});
	}

}
