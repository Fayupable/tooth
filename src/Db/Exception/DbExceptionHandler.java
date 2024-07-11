package Db.Exception;

public class DbExceptionHandler extends Exception {
    public DbExceptionHandler() {
        super();
    }

    public DbExceptionHandler(String message) {
        super(message);
    }

    public DbExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public DbExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}



