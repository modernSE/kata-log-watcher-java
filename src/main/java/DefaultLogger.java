import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultLogger implements Logger {
	final private List<String> lines = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see Logger#popNextLine()
	 */
	@Override
	public Optional<String> popNextLine() {
		final Optional<String> currentLine = Log.popNextLine();
		if(currentLine.isPresent())
			lines.add(currentLine.get());
		
		return currentLine;
	}
	
	/* (non-Javadoc)
	 * @see Logger#getLastTraceOutput()
	 */
	@Override
	public String getLastTraceOutput() {
		for(int i = lines.size() - 1; i >= 0; i--)
			if(lines.get(i).contains("trace"))
				return lines.get(i);
		
		return null;
	}
}
