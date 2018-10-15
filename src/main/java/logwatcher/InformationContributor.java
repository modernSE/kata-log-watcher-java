package logwatcher;

import java.util.Optional;

public interface InformationContributor {

	Optional<String> additionalInformation(LogEntryWithContext context);

}
