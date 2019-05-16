import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class MockLog implements ILog {

	private Queue<String> lines = new ArrayBlockingQueue<String>(10);
	
	public void addLine(String line) {
		lines.add(line);
	}
	
	@Override
	public Optional<String> popNextLine() {
		return Optional.ofNullable(lines.poll());
	}

}
