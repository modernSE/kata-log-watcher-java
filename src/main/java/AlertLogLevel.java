public class AlertLogLevel implements LogLinePredicate {
    private final LogLevel level;

    public AlertLogLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public boolean shouldAlert(LogLine logLine) {
        return logLine.getLevel() == level;
    }
}
