package logwatcher;

import java.time.Clock;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import logwatcher.MailNotifier.MailSender;

public class LogWatcherFactory {

	public static LogWatcher createDefaultConfiguration(Supplier<String> developerOnCallMailAddress, Supplier<String> developerOnCallChatUsername) {
		return createWithCustomSendersAndClock(developerOnCallMailAddress, developerOnCallChatUsername, Util::writeEmail, Util::sendChatMessage, Clock.systemDefaultZone());
	}

	public static LogWatcher createWithCustomSendersAndClock(Supplier<String> developerOnCallMailAddress, Supplier<String> developerOnCallChatUsername, MailSender mailSender, BiConsumer<String, String> chatSender, Clock clock) {
		return new LogWatcher(
				new DefaultLogParser(),
				Set.of(
						new SeverityFilterNotifierDecorator(
								new TimeBasedNotifierDecorator(
										clock,
										new MailNotifier(
												Set.of(
														new Exception42MailRecipientProvider(Collections.singleton("Robert Glaser")),
														new GeneralNotificationForErrors(Stream.of("Antonia Materazzo", "Fritz Schnitzel", "Britta Glatt", "Michael Grün").collect(Collectors.toSet()))
												),
												Set.of(
														new Exception42InformationContrinbutor()
												),
												mailSender
										),
										Predicate.not(TimeBasedPredicates::isOnTheWeekend)
								),
								EnumSet.of(LogLevel.ERROR)
						),
						new SeverityFilterNotifierDecorator(
								new TimeBasedNotifierDecorator(
										clock,
										new ChatNotifier("#theChannel", chatSender),
										TimeBasedPredicates::isDuringWorkingHours
								),
								EnumSet.of(LogLevel.ERROR)
						),
						new TimeBasedNotifierDecorator(
								clock,
								new SeverityFilterNotifierDecorator(
										new OnThresholdReachedNotifier(10, () -> {
											String message = "At least 10 errors were recorded, check it as you are on call!";
											mailSender.send(developerOnCallMailAddress.get(), message);
											chatSender.accept(developerOnCallChatUsername.get(), message);
										}),
										EnumSet.of(LogLevel.ERROR)
								),
								TimeBasedPredicates::isOnTheWeekend))
		);
	}

}
