public class Message {

    private String message;
    private String stackTrace;
    private int statusCode;

    public Message(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public Message(String message, String stackTrace, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
