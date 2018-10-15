package logwatcher;

import java.time.Clock;

import org.junit.jupiter.api.Test;

class LogWatcherFactoryTest {

	@Test
	void shouldCreateDefaultConfiguration() {
		LogWatcherFactory.createDefaultConfiguration(() -> "", () -> "");
	}

	@Test
	void shouldCreateCustomConfiguration() {
		LogWatcherFactory.createWithCustomSendersAndClock(() -> "", () -> "", (mail, message) -> {
		}, (channel, message) -> {
		}, Clock.systemDefaultZone());
	}

}