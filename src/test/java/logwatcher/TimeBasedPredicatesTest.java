package logwatcher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

class TimeBasedPredicatesTest {

	@Test
	void shouldDetermineWorkingHoursCorrectly() {
		LocalDateTime beforeWorkingHours = LocalDateTime.of(2020, 5, 11, 6, 0);
		LocalDateTime duringWorkingHours = LocalDateTime.of(2020, 5, 11, 12, 0);
		LocalDateTime afterWorkingHours = LocalDateTime.of(2020, 5, 11, 18, 0);

		ZoneId zoneId = ZoneId.of("Europe/Berlin");
		ZoneOffset zoneOffset = ZoneOffset.ofHours(2);

		assertFalse(TimeBasedPredicates.isDuringWorkingHours(Clock.fixed(beforeWorkingHours.toInstant(zoneOffset), zoneId)));
		assertTrue(TimeBasedPredicates.isDuringWorkingHours(Clock.fixed(duringWorkingHours.toInstant(zoneOffset), zoneId)));
		assertFalse(TimeBasedPredicates.isDuringWorkingHours(Clock.fixed(afterWorkingHours.toInstant(zoneOffset), zoneId)));
	}

	@Test
	void shouldDetermineWeekendCorrectly() {
		LocalDateTime notWeekend = LocalDateTime.of(2020, 5, 11, 12, 0);
		LocalDateTime onSaturday = LocalDateTime.of(2020, 5, 9, 12, 0);
		LocalDateTime onSunday = LocalDateTime.of(2020, 5, 10, 12, 0);

		ZoneId zoneId = ZoneId.of("Europe/Berlin");
		ZoneOffset zoneOffset = ZoneOffset.ofHours(2);

		assertFalse(TimeBasedPredicates.isOnTheWeekend(Clock.fixed(notWeekend.toInstant(zoneOffset), zoneId)));
		assertTrue(TimeBasedPredicates.isOnTheWeekend(Clock.fixed(onSaturday.toInstant(zoneOffset), zoneId)));
		assertTrue(TimeBasedPredicates.isOnTheWeekend(Clock.fixed(onSunday.toInstant(zoneOffset), zoneId)));
	}

}