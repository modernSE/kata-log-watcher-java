import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

import javax.swing.event.ListSelectionEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcherTest {

	private List<INotifier> notifier;
	private List<User> subscribers;
	private TestUtil testUtil;
	
	@Test
    public void testLogWatcherError() {
        init();
        
		LogWatcher logWatcher = new LogWatcher(testUtil, new TestErrorStateGetter(LogMessage.ERROR), subscribers, notifier);
        logWatcher.watchAndAlert();
        assertEquals(5, testUtil.getNamesCalled().size());
    }

	private void init() {
		testUtil = new TestUtil();
        subscribers = getSubscribers();
        notifier = new ArrayList<>();
        notifier.add(new EmailNotifier(testUtil));
        notifier.add(new GroupChatNotifier(new WorkingHoursUtil(true)));
	}
	
	@Test
	public void testLogWatcherTrace() {
		init();
		
		LogWatcher logWatcher = new LogWatcher(testUtil, new TestErrorStateGetter(LogMessage.TRACE), subscribers, notifier);
		logWatcher.watchAndAlert();
		assertEquals(0, testUtil.getNamesCalled().size());
	}
	
	@Test
	public void testLogWatcherCode42() {
		init();
		
		LogWatcher logWatcher = new LogWatcher(testUtil, new TestErrorStateGetter(LogMessage.CODE42), subscribers, notifier);
		logWatcher.watchAndAlert();
		assertEquals("Robert Glaser@cas.de", testUtil.getNamesCalled().get(0).getAddress());
	}
	
	private List<User> getSubscribers() {
		List<User> subscribers =  new ArrayList<>();
        subscribers.add(new User("Robert Glaser", errorAndCOde42List()));
        subscribers.add(new User("Britta Glatt", errorList()));
        subscribers.add(new User("Michael Grün", errorList()));
        subscribers.add(new User("Antonio Materazzo", errorList()));
        subscribers.add(new User("Fritz Schnitzel", errorList()));
		return subscribers;
	}

	private ArrayList<LogMessage> errorList() {
		ArrayList<LogMessage> arrayList = new ArrayList<>();
		arrayList.add(LogMessage.ERROR);
		return arrayList;
	}
	
	private ArrayList<LogMessage> errorAndCOde42List() {
		ArrayList<LogMessage> arrayList = new ArrayList<>();
		arrayList.add(LogMessage.ERROR);
		arrayList.add(LogMessage.CODE42);
		return arrayList;
	}
	
	
	private class TestUtil extends Util {
		
		List<Mail> namesCalled = new ArrayList<>();
		
		@Override
		public void writeEmail(Mail mail) {
			super.writeEmail(mail);
			namesCalled.add(mail);
		}
		
		
		public List<Mail> getNamesCalled() {
			return namesCalled;
		}
	}
	
	private class TestErrorStateGetter extends ErrorStateGetter {
		
		private LogMessage logMessage;

		public TestErrorStateGetter(LogMessage logMessage) {
			this.logMessage = logMessage;
		}
		
		@Override
		public Optional<LogMessage> getErrorMessage() {
			return Optional.of(logMessage);
		}
	}

}
