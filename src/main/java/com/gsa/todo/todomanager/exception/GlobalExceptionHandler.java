package com.gsa.todo.todomanager.exception;

import com.gsa.todo.todomanager.cantrollers.TodoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
       public ResponseEntity<String> handlerNullPointerException(NullPointerException ex){
           return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handlerNullPointerException(NumberFormatException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handling Resourse not found exception
   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
       logger.error("Errror : {}",ex.getMessage());
       ExceptionResponse response=new ExceptionResponse();
       response.setMessage(ex.getMessage());
       response.setStatus(HttpStatus.NOT_FOUND);
       response.setSuccess(false);

       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
