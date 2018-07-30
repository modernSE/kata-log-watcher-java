
public interface ISubscriber {
	int getMinLogLevel();
	void writeMessage(final String msg);
}
