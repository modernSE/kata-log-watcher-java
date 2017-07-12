import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public class Main {

	public static void main(String[] args) {
		Subscriber robertGlaser = new Subscriber("Robert", "Glaser", Sets.newHashSet(LogEntryType.EXCEPTION42));
		Subscriber brittaGlatt = new Subscriber("Britta", "Glatt", Sets.newHashSet(LogEntryType.ERROR));
		Subscriber michaelGruen = new Subscriber("Michael", "Grün", Sets.newHashSet(LogEntryType.ERROR));
		Subscriber antonioMaterazzo = new Subscriber("Antonio", "Materazzo", Sets.newHashSet(LogEntryType.ERROR));
		Subscriber fritzSchnitzel = new Subscriber("Fritz", "Schnitzel", Sets.newHashSet(LogEntryType.ERROR));
		
		LogWatcher logWatcher = new LogWatcher();
		logWatcher.addSubscriber(robertGlaser);
		logWatcher.addSubscriber(brittaGlatt);
		logWatcher.addSubscriber(michaelGruen);
		logWatcher.addSubscriber(antonioMaterazzo);
		logWatcher.addSubscriber(fritzSchnitzel);

	}

}
