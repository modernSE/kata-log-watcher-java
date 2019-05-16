
public class EmailCommunicationChannel implements CommunicationChannel {

	@Override
	public void write(String name, String message) {
        name = name.toLowerCase();
        name.replace("ü", "ue");
        name.replace("ä", "ae");
        name.replace("ö", "oe");
        name.replace(" ", ".");
        name = name + "@cas.de";
        
        Util.writeEmail(name, message);
	}

}
