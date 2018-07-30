/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class EMailSubscriber implements ISubscriber{

	private String name;
	private int minLogLevel;
	
	public EMailSubscriber(String name, int minLogLevel) {
		this.name = name;
		this.minLogLevel = minLogLevel;
	}
	
    public void writeMessage(final String msg) {
    	System.out.println("Notifying " + getEmailName() + ": " + msg);
    }
    
    private String getEmailName() {
        String emailname = name.toLowerCase();
        emailname.replace("ü", "ue");
        emailname.replace("ä", "ae");
        emailname.replace("ö", "oe");
        emailname.replace(" ", ".");
        return emailname + "@cas.de";
    }

	@Override
	public int getMinLogLevel() {
		return minLogLevel;
	}
}
