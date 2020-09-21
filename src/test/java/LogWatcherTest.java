import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
        // arrange
        List<Subscriber> subscribers = List.of(new Subscriber("testSubscriber", Subscriber.LogLevel.ALL));
        MockConsumer mockConsumer = new MockConsumer();
        LogWatcher logWatcher = new LogWatcher(subscribers, mockConsumer);
        logWatcher.watchAndAlert();
        
        String result = mockConsumer.getResultString();
        assertNotNull(result);
        assertTrue(result.contains("testsubscriber@cas.de: "));
    }
}
