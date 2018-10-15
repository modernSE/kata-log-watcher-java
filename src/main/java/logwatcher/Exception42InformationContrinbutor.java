package logwatcher;

import java.util.Optional;
import java.util.stream.Collectors;

public class Exception42InformationContrinbutor implements InformationContributor {
	@Override
	public Optional<String> additionalInformation(LogEntryWithContext context) {
		LogEntry current = context.getCurrent();
		if (current.getSeverity() == LogLevel.ERROR && current.getCode() == 42) {
			String additionalInformation = context.getPreviousMessagesForLevel(LogLevel.TRACE).stream().map(LogEntry::getText).collect(Collectors.joining(","));
			return additionalInformation.isEmpty() ? Optional.empty() : Optional.of("Previous Trace Level information: " + additionalInformation);
		} else {
			return Optional.empty();
		}
	}
}
