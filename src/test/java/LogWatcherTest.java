import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
    	
    	 List<Subscriber> subscribers = Arrays.asList(
    			 new Subscriber("Robert Glaser", Arrays.asList("ExceptionCode 42")),
    			 new Subscriber("Britta Glatt", Arrays.asList("An error occured")),
    			 new Subscriber("Antonio Materazzo", Arrays.asList("An error occured")),
    			 new Subscriber("Fritz Schnitzel", Arrays.asList("An error occured")),
    			 new Subscriber("Michael Grün", Arrays.asList("An error occured"))
    			 );
    	
        LogWatcher logWatcher = new LogWatcher(subscribers, new DefaultLogger(), new DefaultMailClient());
        logWatcher.watchAndAlert();
        Assert.fail();
    }
}
