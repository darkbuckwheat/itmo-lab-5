package Exceptions;

public class NumberOfArgumentException extends Exception{
    public NumberOfArgumentException() {
    }

    public NumberOfArgumentException(String message) {
        super(message);
    }

    public NumberOfArgumentException(Throwable cause) {
        super(cause);
    }

    public NumberOfArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfArgumentException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NumberOfArgumentException(String commandName, int need, int received) {
        super(commandName + " takes " + need + " arguments. Received " + received);
    }

    public NumberOfArgumentException(String commandName, int received) {
        super(commandName + " takes no arguments. Received " + received);
    }
}
