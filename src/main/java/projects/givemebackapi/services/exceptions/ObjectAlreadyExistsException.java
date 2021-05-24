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


// @ExceptionHandler(ObjectAlreadyExistsException.class)
// public ResponseEntity<StandardError> ObjectAlreadyExistsException(ObjectAlreadyExistsException exception,
//         ServletRequest request) {

//     StandardError error = new StandardError(
//         System.currentTimeMillis(), 
//         HttpStatus.NOT_FOUND.value(),
//         exception.getMessage());

//     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
// }