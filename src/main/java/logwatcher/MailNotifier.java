package logwatcher;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MailNotifier implements Notifier {

	private final Set<MailRecipientProvider> mailRecipientProviders;
	private final Set<InformationContributor> informationContributor;
	private final MailSender mailSender;

	public MailNotifier(Set<MailRecipientProvider> mailRecipientProviders, Set<InformationContributor> informationContributor, MailSender mailSender) {
		this.mailRecipientProviders = mailRecipientProviders;
		this.informationContributor = informationContributor;
		this.mailSender = mailSender;
	}

	@Override
	public void notify(LogEntryWithContext context) {
		Set<String> recipients = mailRecipientProviders.stream().flatMap(filter -> filter.recipientAddresses(context.getCurrent()).stream()).collect(Collectors.toSet());
		String defaultMessage = context.getCurrent().getText() + "\n\nAdditional Information:\n";
		String additionlContent = informationContributor.stream().map(contributor -> contributor.additionalInformation(context)).flatMap(Optional::stream).collect(Collectors.joining("\n\n"));
		recipients.forEach(recipient -> sendMail(recipient, defaultMessage + additionlContent));
	}

	private void sendMail(String recipient, String message) {
		String mailAddress = recipient;
		mailAddress = mailAddress.toLowerCase();
		mailAddress = mailAddress.replace("ä", "oe");
		mailAddress = mailAddress.replace("ö", "ae");
		mailAddress = mailAddress.replace("ü", "ue");
		mailAddress = mailAddress.replace(" ", ".");
		mailAddress = mailAddress + "@cas.de";
		mailSender.send(mailAddress, message);
	}

	public interface MailSender {

		void send(String recipient, String mailBody);

	}
}
