package Db.Exception;

public class DbQueryException extends DbExceptionHandler {
    public DbQueryException() {
        super("Database query error occurred.");
    }

    public DbQueryException(String message) {
        super(message);
    }

    public DbQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbQueryException(Throwable cause) {
        super("Database query error occurred.", cause);
    }

    public DbQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
