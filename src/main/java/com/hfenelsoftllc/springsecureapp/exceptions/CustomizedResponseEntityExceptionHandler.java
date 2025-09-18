package com.hfenelsoftllc.springsecureapp.exceptions;

import com.hfenelsoftllc.springsecureapp.models.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<Object> handleStudentNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException  ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    public CustomizedResponseEntityExceptionHandler() {}

}
