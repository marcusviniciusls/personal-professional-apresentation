package br.com.marcus.dev.personal.professional.apresentation.exception.central;

import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorDateInitAfterDateFinish;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorSavingRecordException;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest) {
        String error = "OBJECT NOT FOUND EXCEPTION";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, resourceNotFoundException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> enumInvalid(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest) {
        String error = "CONSTRAINT NOT BLANK";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, methodArgumentNotValidException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(ErrorSavingRecordException.class)
    public ResponseEntity<StandardError> errorSavingRecordException(ErrorSavingRecordException errorSavingRecordException, HttpServletRequest httpServletRequest) {
        String error = "ERROR SAVING RECORD EXCEPTION";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, errorSavingRecordException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(ErrorDateInitAfterDateFinish.class)
    public ResponseEntity<StandardError> errorDateInitAfterDateFinish(ErrorDateInitAfterDateFinish errorDateInitAfterDateFinish, HttpServletRequest httpServletRequest) {
        String error = "ERROR DATE INIT BEFORE DATE FINISH";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, errorDateInitAfterDateFinish.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException illegalArgumentException, HttpServletRequest httpServletRequest) {
        String error = "ENUM INCORRECT";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, illegalArgumentException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
