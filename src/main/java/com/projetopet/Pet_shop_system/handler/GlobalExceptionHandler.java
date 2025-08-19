package com.projetopet.Pet_shop_system.handler;

import com.projetopet.Pet_shop_system.exceptions.IntegrityViolationException;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException exception, HttpServletRequest http) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError sdr = new StandardError(Instant.now(),status.value(),error,exception.getMessage(),http.getRequestURI());
        return ResponseEntity.status(status).body(sdr);
    }
    @ExceptionHandler(IntegrityViolationException.class)
    public ResponseEntity<StandardError> integrityViolation(IntegrityViolationException e, HttpServletRequest http){
        String erro = "Integrity violation";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError edr = new StandardError(Instant.now(),status.value(),erro,e.getMessage(),http.getRequestURI());
        return ResponseEntity.status(status).body(edr);
    }
}
