import java.util.List;

public class Mailer {
	public void sendMails(List<Mail> mails) {
		for (Mail mail: mails) {
			Util.writeEmail(mail.getName(), mail.getLogMessage());
		}
	}
}
