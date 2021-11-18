import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
        LogWatcher logWatcher = new LogWatcher();

        String actual1 = logWatcher.watchAndAlert(10);
        Assert.assertEquals("", actual1);

        String actual2 = logWatcher.watchAndAlert(11);
        Assert.assertEquals("Notifying robert glaser@cas.de: ExceptionCode 42\nNotifying britta glatt@cas.de: ExceptionCode 42\nNotifying michael grün@cas.de: ExceptionCode 42\nNotifying antonio materazzo@cas.de: ExceptionCode 42\nNotifying fritz schnitzel@cas.de: ExceptionCode 42\n", actual2);

        String actual3 = logWatcher.watchAndAlert(7);
        Assert.assertEquals("", actual3);

        String actual4 = logWatcher.watchAndAlert(3);
        Assert.assertEquals("Notifying britta glatt@cas.de: An error occured\nNotifying michael grün@cas.de: An error occured\nNotifying antonio materazzo@cas.de: An error occured\nNotifying fritz schnitzel@cas.de: An error occured\n", actual4);
    }
}
