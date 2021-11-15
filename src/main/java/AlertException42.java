public class AlertException42 implements LogLinePredicate {
    private static final String EXCEPTION_42 = "ExceptionCode 42";

    @Override
    public boolean shouldAlert(LogLine logLine) {
        return logLine.getMessage().equals(EXCEPTION_42);
    }
    
}
