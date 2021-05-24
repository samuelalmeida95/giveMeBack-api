package projects.givemebackapi.services.exceptions;

public class ObjectAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }  
}
