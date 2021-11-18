@FunctionalInterface
public interface LogLinePredicate {
    boolean shouldAlert(LogLine logLine);

    default LogLinePredicate andAlso(LogLinePredicate other) {
        return logLine -> this.shouldAlert(logLine) && other.shouldAlert(logLine);
    }
}
