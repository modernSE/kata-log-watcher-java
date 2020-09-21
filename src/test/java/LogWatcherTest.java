import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    private static final String[] subscribers = {"Robert Glaser", "Britta Glatt", "Michael Grün"};
    private static LogEntryFilter filter = new LogEntryFilter(List.of("An error occured"));

    @Test
    public void testLogWatcher() {
        LogWatcher logWatcher = new LogWatcher(subscribers, filter);
        Optional<String> logEntry = Optional.empty();
        while(!filter.checkLogEntry(logEntry)){
            logEntry = logWatcher.watch();
        }
        assertTrue(logWatcher.alert(logEntry));
    }
}
