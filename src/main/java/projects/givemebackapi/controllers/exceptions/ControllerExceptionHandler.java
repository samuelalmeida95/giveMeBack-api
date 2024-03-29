package projects.givemebackapi.controllers.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import projects.givemebackapi.services.exceptions.NoSuchElementException;
import projects.givemebackapi.services.exceptions.ObjectAlreadyExistsException;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception,
    ServletRequest request) {
        
        StandardError error = new StandardError(
            System.currentTimeMillis(), 
            HttpStatus.NOT_FOUND.value(),
            exception.getMessage());
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        
        @ExceptionHandler(ObjectAlreadyExistsException.class)
        public ResponseEntity<StandardError> ObjectAlreadyExistsExcpetion(ObjectAlreadyExistsException exception,
                ServletRequest request) {
    
            StandardError error = new 
                StandardError(System.currentTimeMillis(), 
                             HttpStatus.BAD_REQUEST.value(),
                             exception.getMessage());
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException exception,
            ServletRequest request) {

        StandardError error = new 
            StandardError(System.currentTimeMillis(), 
                         HttpStatus.BAD_REQUEST.value(),
                         exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e, ServletRequest request) {

        ValidationError error = new
             ValidationError(System.currentTimeMillis()
                        ,HttpStatus.BAD_REQUEST.value()
                        ,"Erro na validação dos campos!");

        for(FieldError erro: e.getBindingResult().getFieldErrors()) {
            error.addErrors(erro.getField(),erro.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> NoSuchElementException(NoSuchElementException e, ServletRequest request) {

        StandardError error = new 
        StandardError(System.currentTimeMillis(), 
                     HttpStatus.BAD_REQUEST.value(),
                     e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
