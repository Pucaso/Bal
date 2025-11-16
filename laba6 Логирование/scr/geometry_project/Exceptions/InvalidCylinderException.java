package Exceptions;

public class InvalidCylinderException extends Exception {
    public InvalidCylinderException(String message) {
        super(message);
    }
    
    public InvalidCylinderException(String message, Throwable cause) {
        super(message, cause);
    }
}