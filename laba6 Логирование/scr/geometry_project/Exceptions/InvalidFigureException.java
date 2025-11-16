package Exceptions;

public class InvalidFigureException extends Exception {
    public InvalidFigureException(String message) {
        super(message);
    }
    
    public InvalidFigureException(String message, Throwable cause) {
        super(message, cause);
    }
}