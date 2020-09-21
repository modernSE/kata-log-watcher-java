import java.util.List;
import java.util.Optional;

public class LogEntryFilter {

    List<String> validLogEntries;
    public LogEntryFilter(List<String> validLogEntries){
        this.validLogEntries = validLogEntries;
    }
    public boolean checkLogEntry(Optional<String> logEntry){
        if(logEntry.isPresent()){
            return validLogEntries.contains(logEntry.get());
        }
        return false;
    }
    
}