import java.util.Optional;

public class DefaultLog implements ILog {

	private Log log = new Log();
	
	@Override
	public Optional<String> popNextLine() {
		return log.popNextLine();
	}

}
