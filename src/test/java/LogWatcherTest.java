import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    private static final List<String> NAMES = List.of("Robert Glaser", "Britta Glatt", "Michael Grün");
    private static final List<String> ADDRESSES = List.of("robert.glaser@cas.de", "britta.glatt@cas.de", "michael.gruen@cas.de");

    @Test
    public void testLogWatcher() {
        // arrange
        LogProvider logProvider = new DeterministicLogProvider(Optional.of(error("An error")), Optional.empty(), Optional.of(error("Another error")));
        DummyEmailService emailService = new DummyEmailService();
        LogWatcher logWatcher = new LogWatcher(logProvider, emailService);
        addTestSubscribers(logWatcher);

        // act
        logWatcher.watchAndAlert();

        // assert
        assertEquals(assembleEmails(ADDRESSES, List.of("An error")), emailService.getEmails());
    }

    @Test
    public void testLogWatcher2() {
        // arrange
        LogProvider logProvider = new DeterministicLogProvider(Optional.of(error("An error")), Optional.empty(), Optional.of(error("Another error")));
        DummyEmailService emailService = new DummyEmailService();
        LogWatcher logWatcher = new LogWatcher(logProvider, emailService);
        addTestSubscribers(logWatcher);

        // act
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();

        // assert
        assertEquals(assembleEmails(ADDRESSES, List.of("An error")), emailService.getEmails());
    }

    @Test
    public void testLogWatcher3() {
        // arrange
        LogProvider logProvider = new DeterministicLogProvider(Optional.of(error("An error")), Optional.empty(), Optional.of(error("Another error")));
        DummyEmailService emailService = new DummyEmailService();
        LogWatcher logWatcher = new LogWatcher(logProvider, emailService);
        addTestSubscribers(logWatcher);

        // act
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();

        // assert
        assertEquals(assembleEmails(ADDRESSES, List.of("An error", "Another error")), emailService.getEmails());
    }

    @Test
    public void watchAndAlert_SHOULD_ignoreDebugAndTraceLogs() {
        // arrange
        LogProvider logProvider = new DeterministicLogProvider(Optional.of(debug("Debug")), Optional.of(trace("Trace")));
        DummyEmailService emailService = new DummyEmailService();
        LogWatcher logWatcher = new LogWatcher(logProvider, emailService);
        addTestSubscribers(logWatcher);

        // act
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();

        // assert
        assertEquals(List.of(), emailService.getEmails());
    }
    
    private void addTestSubscribers(LogWatcher logWatcher) {
        for (String name : NAMES) {
            logWatcher.addSubscriber(new Person(name));
        }
    }

    private LogLine error(String message) {
        return new LogLine(LogLevel.ERROR, message);
    }

    private LogLine debug(String message) {
        return new LogLine(LogLevel.DEBUG, message);
    }

    private LogLine trace(String message) {
        return new LogLine(LogLevel.TRACE, message);
    }

    private List<Email> assembleEmails(List<String> addresses, List<String> messages) {
        List<Email> result = new ArrayList<>();
        for (String message : messages) {
            for (String address : addresses) {
                result.add(new Email(address, message));
            }
        }
        return result;
    }

    private class DummyEmailService implements EmailService {
        private List<Email> emails = new ArrayList<>();

        @Override
        public void writeEmail(Email email) {
            emails.add(email);
        }

        public List<Email> getEmails() {
            return emails;
        }
        
    }

    private class DeterministicLogProvider implements LogProvider {
        private Optional<LogLine>[] fixedValues;
        private int currentIndex = 0;

        @SafeVarargs
        public DeterministicLogProvider(Optional<LogLine>... fixedValues) {
            this.fixedValues = fixedValues;
        }

        @Override
        public Optional<LogLine> popNextLine() {
            if (currentIndex == fixedValues.length) {
                fail();
            }

            Optional<LogLine> result = fixedValues[currentIndex];
            currentIndex++;
            return result;
        }
    }
}
