package projects.givemebackapi.services.exceptions;

public class NoSuchElementException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public NoSuchElementException(String message) {
        super(message);
    }

    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
