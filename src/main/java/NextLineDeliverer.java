import java.util.ArrayList;
import java.util.Optional;

public class NextLineDeliverer {

	private ArrayList<String> messagesStack = new ArrayList<>();
	private ArrayList<String> errorStack = new ArrayList<>();

	public Optional<String> popNextLine() {
		Optional<String> nextLine = Log.popNextLine();
		nextLine.ifPresent(s -> messagesStack.add(s));
		nextLine.ifPresent(s -> checkIfError(s));
		return nextLine;
	}

	private void checkIfError(String s) {
		if (Log.AN_ERROR_OCCURED.equals(s)) {
			errorStack.add(s);
		}
	}

	public int numOfErrors() {
		return errorStack.size();
	}
	public String createMessageWithTrace() {
		StringBuilder t = new StringBuilder();
		messagesStack.stream().forEach(s -> t.append(s + "\n"));
		return t.toString();
	}
	
	public String createMessageWithErrors() {
		StringBuilder t = new StringBuilder();
		errorStack.stream().forEach(s -> t.append(s + "\n"));
		return t.toString();
	}

	public void clearTrace() {
		messagesStack.clear();
		errorStack.clear();
	}
	
	public void clearErrorStack() {
		errorStack.clear();
	}
	
}
