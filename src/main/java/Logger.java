import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface Logger {
	
	Optional<String> popNextLine();

}
