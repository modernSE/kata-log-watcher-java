import java.util.Arrays;
import java.util.List;

public class Subscriber {
	
	private String name;
	private List<String> relevantLines;
	private List<String> stackTraceRelevantLines;
	
	public Subscriber(String name, String... relevantLines) {
		this.name = name;
		this.relevantLines = Arrays.asList(relevantLines);
	}
	
	public String getName() {
		return name;
	}

	public boolean isLineRelevant(String line) {
		return relevantLines.contains(line);
	}
	
	public boolean isStackTraceRelevant(String line) {
		return "ExceptionCode 42".equals(line);
		
	}

}
