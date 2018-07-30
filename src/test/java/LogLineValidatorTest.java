import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogLineValidatorTest {

	private LogLineValidator logLineValidator;

	@Before
	public void setup() {
		logLineValidator = new LogLineValidator();
	}
	
    @Test
    public void noLogLine_shouldBeValidLogLine() {
        Optional<String> nullOptional = Optional.empty();
		Assert.assertFalse(logLineValidator.isErrorLog(nullOptional));
    }
    
    @Test
    public void error_shouldBeInvalidLogLine() {
        Optional<String> errorOptional = Optional.of("An error occured");
		Assert.assertTrue(logLineValidator.isErrorLog(errorOptional));
    }
}
