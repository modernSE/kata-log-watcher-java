import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() throws InterruptedException {
        LogWatcher logWatcher = new LogWatcher();
//        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
        for (int i = 0; i < 50; i++) {
        	logWatcher.watchAndAlert();
        	TimeUnit.SECONDS.sleep(1);
        }

        
//        assertEquals("Notifying robert glaser@cas.de: ExceptionCode 42", outContent.toString());
    }
}
