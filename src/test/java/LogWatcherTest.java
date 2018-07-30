import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

	private LogWatcher logWatcher;
	
	@Before
	public void init() {
		this.logWatcher = new LogWatcher();
	}
	
    @Test
    public void testLogWatcher() {
    	
    	for(int i=0; i<10000; i++) {
    		Optional<String> popNextLine = Log.popNextLine();
            logWatcher.watchAndAlert(popNextLine);
    	}   	
    }
    
    @Test
    public void createNotificationsTest() {
    	
    	final String normalMessage = "TEST_TEST";
    	assertTrue(logWatcher.createNotifications(normalMessage).size() == 0);
    	System.out.println("---");
    	System.out.println(logWatcher.createNotifications(Subscription.availableSubscriptions[0].name).size());
    	System.out.println("---");
		assertTrue(logWatcher.createNotifications(Subscription.availableSubscriptions[0].name).size() == User.subscribers.length + 1);	
		assertTrue(logWatcher.createNotifications(Subscription.availableSubscriptions[1].name).size() == User.subscribers.length - 1);	

    }
    
    @Test
    public void shouldEmailBeSendTest() {
    	
    	Subscription invalidSubscription = new Subscription("INVALID");
    	assertFalse(this.logWatcher.shouldBeSend("TEST_TEST", invalidSubscription));
    	
    	assertTrue(this.logWatcher.shouldBeSend("ExceptionCode 42", Subscription.availableSubscriptions[0]));

    	assertTrue(this.logWatcher.shouldBeSend("An error occured", Subscription.availableSubscriptions[1]));
    }
    
    @Test
    public void createEmailAddressTest() {
    	
    	final String testNameOne = "Ü_Ä_Ö_ ";
    	System.out.println(testNameOne.toLowerCase());
    	final String expectedEmailAddressOne = "ue_ae_oe_.@cas.de";
    	
    	final String actualEmailAddress = this.logWatcher.createEmailAddress(testNameOne);
        System.out.println(actualEmailAddress);
    	assertTrue(expectedEmailAddressOne.equals(actualEmailAddress));
    }
}
