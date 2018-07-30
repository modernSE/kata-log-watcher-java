import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
    	ISubscriber roberGlaser = new EMailSubscriber("Robert Glaser", LogLevel.Error42);
    	
    	ISubscriber[] subscribers = { roberGlaser,
    			new EMailSubscriber("Britta Glatt", LogLevel.Error),
    			new EMailSubscriber("Michael Grün", LogLevel.Error),
    			new EMailSubscriber("Antonio Matarazzo", LogLevel.Error),
    			new EMailSubscriber("Fritz Schnitzel", LogLevel.Error),
    			new ChatBotSubscriber(), 
    			new OnCallSubscriber(roberGlaser)};
    	
        LogWatcher logWatcher = new LogWatcher(subscribers);
        
        try {
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();
	        System.out.println("--------------------");
	        Thread.sleep(200, 0);
	        logWatcher.watchAndAlert();        
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
