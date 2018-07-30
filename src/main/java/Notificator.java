
public class Notificator {

	public void notifyUser(String userEmail, String msg) {
		Util.writeEmail(userEmail, msg);
	}
	
	public void notifyUsersInChat(String chatGroup, String msg) {
		System.out.println("Notified " + msg + " in " + chatGroup);
	}
}
