import java.util.HashMap;
import java.util.Map;

public class Subscriber{
    
    private String name;
    private LogLevel logLevel;

    public enum LogLevel {
        ERROR, WARN, INFO, ALL
    }
    
    public Subscriber(String name, LogLevel logLevel) {
        this.name = name;
        this.logLevel = logLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getEmail() {
            String email = name.toLowerCase();
            email.replace("ü", "ue");
            email.replace("ä", "ae");
            email.replace("ö", "oe");
            email.replace(" ", ".");
            return email + "@cas.de";
    }
}