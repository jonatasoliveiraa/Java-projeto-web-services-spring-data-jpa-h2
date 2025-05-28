package com.devsuperior.webservicesjpa.resources.exceptions;


import com.devsuperior.webservicesjpa.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        String path = request.getRequestURI();

        StandardError err = new StandardError(
                Instant.now(), status.value(), error, message, path
        );
        
        return ResponseEntity.status(status).body(err);
    }
}
