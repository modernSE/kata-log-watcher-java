import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import email.Notificator;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

    @Test
    public void testLogWatcher() {
        var notifcator = new TestNotificator(value -> assertFalse(value.isBlank()));
        var logWatcher = new LogWatcher(notifcator);
        var log = Optional.of(Log.ERROR);
        logWatcher.alert(log);
    }
}
