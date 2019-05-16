import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestObserver implements IEmailWriteObserver{
	
	private List<TestLogEvent> events = new ArrayList<TestLogEvent>();
	
	@Override
	public void notify(String name, String logMessage) {
		events.add(new TestLogEvent(name, logMessage));
	}

}
