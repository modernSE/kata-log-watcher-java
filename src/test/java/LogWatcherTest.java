import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    private class MockSubscriber implements Subscriber {

        String name;

        public String logMessage;
        public String formatedName;

        public MockSubscriber(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void onNotify(String formatedName, String message) {
            this.logMessage = message;
            this.formatedName = formatedName;

        }

    }
    @Test
    public void testLogWatcher() {
        LogWatcher logWatcher = new LogWatcher();
        MockSubscriber sub = new MockSubscriber("Mock");
        logWatcher.addSubriber(sub);
        logWatcher.watchAndAlert(2);

        assertTrue(sub.formatedName == null && sub.logMessage == null);
    }
}
