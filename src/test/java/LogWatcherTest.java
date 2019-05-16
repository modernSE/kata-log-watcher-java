import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
    	CommunicationChannel emailCommunicationChannel = new EmailCommunicationChannel();
    	
    	Predicate<String> onlyErrors = s -> s.equals("An error occured");
    	Subscriber britta = Subscriber.createSingleChannel("Britta Glatt", onlyErrors, emailCommunicationChannel);
    	Subscriber antonio = Subscriber.createSingleChannel("Antonio Materazzo", onlyErrors, emailCommunicationChannel);
    	Subscriber robert = Subscriber.createSingleChannel("Robert Glaser", (String s) -> "ExceptionCode 42".equals(s), emailCommunicationChannel);
    	
    	TestReceiver testReceiver = new TestReceiver();
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        subscribers.add(britta);
        subscribers.add(antonio);
        subscribers.add(robert);
        LogWatcher logWatcher = new LogWatcher(subscribers, () -> Optional.of("Hello World"));
        logWatcher.watchAndAlert();
        assertEquals(testReceiver.mailAddress, "robert glaser@cas.de");
        assertEquals(testReceiver.message, "Hello World");
    }
    
    static class TestReceiver implements BiConsumer<String, String> {

		String mailAddress;
		String message;

		@Override
		public void accept(String mailAddress, String message) {
			this.mailAddress = mailAddress;
			this.message = message;
			
		}
    	
    }
}
