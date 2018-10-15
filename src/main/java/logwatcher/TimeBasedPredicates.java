package logwatcher;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public final class TimeBasedPredicates {

	private static final LocalTime WORKING_HOURS_START = LocalTime.of(8, 0);
	private static final LocalTime WORKING_HOURS_END = LocalTime.of(17, 0);

	public static boolean isDuringWorkingHours(Clock clock) {
		LocalDateTime now = LocalDateTime.ofInstant(clock.instant(), ZoneId.of("Europe/Berlin"));
		LocalTime localTime = now.toLocalTime();
		return !isOnTheWeekend(clock) && localTime.isAfter(WORKING_HOURS_START) && localTime.isBefore(WORKING_HOURS_END);
	}

	public static boolean isOnTheWeekend(Clock clock) {
		LocalDateTime now = LocalDateTime.ofInstant(clock.instant(), ZoneId.of("Europe/Berlin"));
		DayOfWeek dayOfWeek = now.getDayOfWeek();
		return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}

}
