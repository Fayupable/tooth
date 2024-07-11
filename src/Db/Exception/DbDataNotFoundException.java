package Db.Exception;

public class DbDataNotFoundException extends DbExceptionHandler {
    public DbDataNotFoundException() {
        super("No data found for the given query.");
    }

    public DbDataNotFoundException(String message) {
        super(message);
    }

    public DbDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbDataNotFoundException(Throwable cause) {
        super("No data found for the given query.", cause);
    }

    public DbDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
