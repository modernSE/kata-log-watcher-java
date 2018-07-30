import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {
	LogWatcher logWatcher;
	NextLineDeliverer nextLineDeliverer;
	Notificator notificator;
	Date date;

	@Before
	public void setUp() throws Exception {
		logWatcher = new LogWatcher();
		
		logWatcher.setSubscribers(Arrays.asList("Robert Glaser", "Britta Glatt", "Michael Grün", 
			    	"Antonio Materazzo", "Fritz Schnitzel"));
		
		logWatcher.setSubscribersToNotifyOnError(Arrays.asList("Britta Glatt", "Michael Grün", 
		    	"Antonio Materazzo", "Fritz Schnitzel"));
		
		logWatcher.setSubscribersToNotifyOnException42((Arrays.asList("Robert Glaser")));
		 date = Mockito.mock(Date.class);
		
		logWatcher.setDate(date);
	
		nextLineDeliverer = Mockito.mock(NextLineDeliverer.class);
		notificator = Mockito.mock(Notificator.class);
		logWatcher.setNotificator(notificator);
		logWatcher.setNextLineDeliverer(nextLineDeliverer);
	}

	@Test
    public void testThatSubscribersNotifiedOnErrorExceptRobert() throws Exception {

        Mockito.when(nextLineDeliverer.popNextLine()).thenReturn(Optional.of(Log.DEBUG_OUTPUT));
        Mockito.when(date.getDay()).thenReturn(1);
        Mockito.when(date.getHours()).thenReturn(15);
        logWatcher.watchAndAlert();
       
        Mockito.verify(notificator, Mockito.times(4)).notifyUser(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(notificator, Mockito.times(1)).notifyUsersInChat(Mockito.anyString(), Mockito.anyString());
        
    }

}
