import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
        LogWatcher logWatcher = new LogWatcher();
        logWatcher.watchAndAlert();
        fail();
    }
}
