package logwatcher;

import java.util.Set;

public interface MailRecipientProvider {

	Set<String> recipientAddresses(LogEntry entry);

}
