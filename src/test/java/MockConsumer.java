import java.util.function.BiConsumer;

public class MockConsumer implements BiConsumer<String, String> {

	
    
    private String resultString;

    @Override
	public void accept(String t, String u) {
        resultString = t + ": " + u;
    }
    
    public String getResultString() {
        return resultString;
    }

}