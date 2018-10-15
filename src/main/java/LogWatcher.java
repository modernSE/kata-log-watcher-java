import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {
	private List<Group> groups = new ArrayList<>();
    private MailGenerator mailGenerator = new MailGenerator();
    private Mailer mailer = new Mailer();
    private Logger logger = new DefaultLogger();
    
    public LogWatcher() {
    	groups.add(new ErrorGroup("Britta Glatt", "Michael Grün", "Antonio Materazzo", "Fritz Schnitzel"));
    	groups.add(new Code42Group("Robert Glaser"));
    }

    public void watchAndAlert() {
        Optional<String> logEntry = logger.popNextLine();
        String = logger.getLastTraceOutput();
        
        if (logEntry.isPresent()) {
        	List<Mail> mails = mailGenerator.generateMails(groups, logEntry.get());
        	mailer.sendMails(mails);
        }
    }
}
