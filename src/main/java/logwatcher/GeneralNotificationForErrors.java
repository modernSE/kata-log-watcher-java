package logwatcher;

import java.util.Collections;
import java.util.Set;

public class GeneralNotificationForErrors implements MailRecipientProvider {

	private Set<String> devTeamEmailAddresses;

	public GeneralNotificationForErrors(Set<String> devTeamEmailAddresses) {
		this.devTeamEmailAddresses = devTeamEmailAddresses;
	}

	@Override
	public Set<String> recipientAddresses(LogEntry entry) {
		if (entry.getSeverity() == LogLevel.ERROR) {
			return devTeamEmailAddresses;
		} else {
			return Collections.emptySet();
		}
	}
}
