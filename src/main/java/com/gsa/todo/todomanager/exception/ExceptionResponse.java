package com.gsa.todo.todomanager.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private String message;
    private  Boolean success;
    private HttpStatus status;

    public ExceptionResponse(String message, Boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

     public ExceptionResponse(){

     }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
