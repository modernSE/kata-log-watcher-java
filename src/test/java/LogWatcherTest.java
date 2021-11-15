import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testSubscriberGetsNotified_WHEN_messageIsPresent() {
        Log log = new Log(2);
        LogWatcher logWatcher = new LogWatcher(log);

        List<Message> loggedMessage = new ArrayList<>();
        logWatcher.addSubscriber(new INotificationSubscriber() {

            @Override
            public void notify(Message message) {
                loggedMessage.add(message);
            }
            
        });
        logWatcher.watchAndAlert();

        
        Assert.assertEquals(loggedMessage.size(), 1);
        Assert.assertEquals(loggedMessage.get(0).getMessage(), Log.DEBUG_OUTPUT);
    }

    @Test
    public void testSubscriberIsNotNotified_WHEN_noMessageIsPresent() {
        Log log = new Log(0);
        LogWatcher logWatcher = new LogWatcher(log);

        List<Message> loggedMessage = new ArrayList<>();
        logWatcher.addSubscriber(new INotificationSubscriber() {

            @Override
            public void notify(Message message) {
                loggedMessage.add(message);
            }
            
        });
        logWatcher.watchAndAlert();

        
        Assert.assertEquals(loggedMessage.size(), 0);
    }

    @Test
    public void testSubscriberIsNotNotified_WHEN_filterDoesNotMatch() {
        Log log = new Log(3);
        LogWatcher logWatcher = new LogWatcher(log);
        logWatcher.setStatusCodeFilter(1);

        List<Message> loggedMessage = new ArrayList<>();
        logWatcher.addSubscriber(new INotificationSubscriber() {

            @Override
            public void notify(Message message) {
                loggedMessage.add(message);
            }
            
        });
        logWatcher.watchAndAlert();

        
        Assert.assertEquals(loggedMessage.size(), 0);
    }

    @Test
    public void testSubscriberIsNotified_WHEN_filterMatches() {
        Log log = new Log(2);
        LogWatcher logWatcher = new LogWatcher(log);
        logWatcher.setStatusCodeFilter(2);

        List<Message> loggedMessage = new ArrayList<>();
        logWatcher.addSubscriber(new INotificationSubscriber() {

            @Override
            public void notify(Message message) {
                loggedMessage.add(message);
            }
            
        });
        logWatcher.watchAndAlert();

        
        Assert.assertEquals(loggedMessage.size(), 1);
        Assert.assertEquals(loggedMessage.get(0).getMessage(), Log.DEBUG_OUTPUT);
    }

    @Test
    public void testStackTraceIsNotEmpty_WHEN_statusCodeIs42() {
        Log log = new Log(42);
        LogWatcher logWatcher = new LogWatcher(log);

        List<Message> loggedMessage = new ArrayList<>();
        logWatcher.addSubscriber(new INotificationSubscriber() {

            @Override
            public void notify(Message message) {
                loggedMessage.add(message);
            }
            
        });
        logWatcher.watchAndAlert();

        
        Assert.assertEquals(loggedMessage.size(), 1);
        Assert.assertEquals(loggedMessage.get(0).getStackTrace(), "A stack trace");
    }
}
