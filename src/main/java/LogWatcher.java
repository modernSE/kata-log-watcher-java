import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    private static final Subscriber[] subscribers = {
        new Subscriber("Robert Glaser", List.of(2)),
        new Subscriber("Britta Glatt", List.of(1,2)),
        new Subscriber("Michael Grün", List.of(1,2)),
        new Subscriber("Antonio Materazzo", List.of(1,2)),
        new Subscriber("Fritz Schnitzel", List.of(1,2))
    };

    public String watchAndAlert(long time) {
        Optional<LogMessage> logEntry = Log.popNextLine(time);

        if (logEntry.isPresent() && logEntry.get().isError()) {
                return notifySubscribers(logEntry.get());
        }

        return "";
    }

    private String notifySubscribers(LogMessage logMessage) {
        StringBuilder messages = new StringBuilder();

        for (int i = 0; i < subscribers.length; i++) {
            if (subscribers[i].getCodesToNotify().contains(logMessage.getCode())) {
                String formattedName = formatName(subscribers[i].getName());
                String mailMessage = buildMessage(formattedName, logMessage.getMessage());
    
                Util.writeEmail(mailMessage);
                messages.append(mailMessage);
            }
        }

        return messages.toString();
    }

    private String buildMessage(String name, String message) {
        return "Notifying " + name + ": " + message + "\n";
    }

    private String formatName(String name) {
        name = name.toLowerCase();
        name.replace("ü", "ue");
        name.replace("ä", "ae");
        name.replace("ö", "oe");
        name.replace(" ", ".");

        return name + "@cas.de";
    }
}
