package logwatcher;

public class Util {

	public static void writeEmail(String address, String msg) {
		System.out.println("Notifying " + address + ": " + msg);
	}

	public static void sendChatMessage(String channel, String message) {
		System.out.println("Notifying Channel " + channel + ": " + message);
	}
}
