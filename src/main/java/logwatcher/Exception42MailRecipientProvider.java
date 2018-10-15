package logwatcher;

import java.util.Collections;
import java.util.Set;

public class Exception42MailRecipientProvider implements MailRecipientProvider {

	private final Set<String> recipientForError42;

	public Exception42MailRecipientProvider(Set<String> recipientForError42) {
		this.recipientForError42 = recipientForError42;
	}

	@Override
	public Set<String> recipientAddresses(LogEntry entry) {
		if (entry.getSeverity() == LogLevel.ERROR && entry.getCode() == 42) {
			return recipientForError42;
		} else {
			return Collections.emptySet();
		}
	}
}
