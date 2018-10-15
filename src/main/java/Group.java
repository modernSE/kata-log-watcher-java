import java.util.List;

public interface Group {
	List<String> getSubscribers();
	
	boolean checkLogMessage(String logMessage);
}
