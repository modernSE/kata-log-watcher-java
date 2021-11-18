public class LogMessage {
        
    final String message;
    final int code;

    public LogMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public boolean isError() {
        return code > 0;
    }
}
