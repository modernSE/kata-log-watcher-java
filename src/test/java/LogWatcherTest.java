import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
        LogWatcher logWatcher = new LogWatcher();
        for(int i = 0; i < 10; i++)
        	logWatcher.watchAndAlert();
        
        Assert.fail();
    }
}
