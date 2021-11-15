import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private List<Person> subscribers = new ArrayList<>();
    private LogProvider logProvider;
    private EmailService emailService;

    public LogWatcher(LogProvider logProvider, EmailService emailService) {
        this.logProvider = logProvider;
        this.emailService = emailService;
    }

    public void addSubscriber(Person newSubscriber) {
        subscribers.add(newSubscriber);
    }

    public void watchAndAlert() {
        Optional<LogLine> logEntry = logProvider.popNextLine();
        logEntry.ifPresent(this::notifySubscribers);
    }

    private void notifySubscribers(LogLine logLine) {

        for (Person subscriber : subscribers) {
            if (subscriber.getPredicate().shouldAlert(logLine)) {
                emailService.writeEmail(new Email(subscriber.getEmailAddress(), logLine.getMessage()));
            }
        }
    }
}
