import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {
	private LogProvider logProvider = new DefaultLogProvider();
	private LogWatcher logWatcher;
	
	@Before
	public void setUp() {
		logWatcher = new LogWatcher(logProvider);
	}

    @Test
    public void testLogWatcher() {
    	logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
        logWatcher.watchAndAlert();
    }
}
