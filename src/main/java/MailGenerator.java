import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MailGenerator {
	public List<Mail> generateMails(List<Group> groups, String logMessage) {
    	List<Mail> mails = new ArrayList<>();
    	Optional<Group> group = groups.stream()
    			.filter(eachGroup -> eachGroup.checkLogMessage(logMessage))
    			.findFirst();
    	
    	if (!group.isPresent()) {
    		return mails;
    	}
    	
        for (String name : group.get().getSubscribers()) {
            name = name.toLowerCase();
            name = name.replace("ü", "ue");
            name = name.replace("ä", "ae");
            name = name.replace("ö", "oe");
            name = name.replace(" ", ".");
            name = name + "@cas.de";
            
            mails.add(new Mail(name, logMessage));
        }
        
        return mails;
    }
}
