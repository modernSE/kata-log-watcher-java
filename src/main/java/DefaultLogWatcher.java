import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class DefaultLogWatcher {

	public Optional<LogType> watch() {
		return Log.popNextLine().flatMap(LogType::from);
	}
}
