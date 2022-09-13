package br.com.marcus.dev.personal.professional.apresentation.exception.central;

import br.com.marcus.dev.personal.professional.apresentation.exception.custom.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.alexaforbusiness.AmazonAlexaForBusinessClient;
import com.amazonaws.services.s3.model.AmazonS3Exception;
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

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> fileException(FileException fileException, HttpServletRequest httpServletRequest) {
        String error = "FILE EXCEPTION";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, fileException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException amazonServiceException, HttpServletRequest httpServletRequest) {
        String error = "AMAZON SERVICE EXCEPTION";
        HttpStatus httpStatus = HttpStatus.valueOf(amazonServiceException.getStatusCode());
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, amazonServiceException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClientException(AmazonClientException amazonClientException, HttpServletRequest httpServletRequest) {
        String error = "AMAZON CLIENT EXCEPTION";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, amazonClientException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception amazonS3Exception, HttpServletRequest httpServletRequest) {
        String error = "AMAZON S3 EXCEPTION";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, amazonS3Exception.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(CurrentJobException.class)
    public ResponseEntity<StandardError> currentJobException(CurrentJobException currentJobException, HttpServletRequest httpServletRequest) {
        String error = "THERE IS A CURRENT JOB";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, currentJobException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
