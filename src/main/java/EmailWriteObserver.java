
public class EmailWriteObserver implements IEmailWriteObserver{

	@Override
	public void notify(String name, String logMessage) {
		Util.writeEmail(name, logMessage);
	}

}
