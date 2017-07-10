import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
        LogWatcher logWatcher = new LogWatcher();
        logWatcher.watchAndAlert();
        Assert.fail();
    }
}
