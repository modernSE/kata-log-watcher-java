import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
    	IEmailWriteObserver testObserver = new TestObserver();
        LogWatcher logWatcher = new LogWatcher(new Log(), testObserver);
        logWatcher.addSubscriber("Robert Glaser", new FourtyTwoLogCondition());
        logWatcher.addSubscriber("Britta Glatt", new EmptyCondition());
        logWatcher.addSubscriber("Michael Grün", new EmptyCondition());
        logWatcher.addSubscriber("Antonio Materazzo", new EmptyCondition());
        logWatcher.addSubscriber("Fritz Schnitzel", new EmptyCondition());
        
        logWatcher.watchAndAlert();
    }
}
