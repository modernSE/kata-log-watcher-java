
public class DefaultMailClient implements MailClient {

	
	@Override
	public void sendMail(String address, String msg) {
		Util.writeEmail(address, msg);

	}

}
