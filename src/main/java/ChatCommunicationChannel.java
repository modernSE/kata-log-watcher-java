
public class ChatCommunicationChannel implements CommunicationChannel {

	@Override
	public void write(String name, String message) {
		System.out.println("Notyfing rocket chat " + name + ": " + message);
	}
}
