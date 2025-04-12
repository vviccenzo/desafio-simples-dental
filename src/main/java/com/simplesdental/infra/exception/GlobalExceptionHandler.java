package com.simplesdental.infra.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex,
            HttpServletRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.NOT_FOUND.value());
        errorAttributes.put("error", "Not Found");
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex,
            HttpServletRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("error", "Bad Request");
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("error", "Bad Request");
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Unhandled exception occurred", ex);

        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorAttributes.put("error", "Internal Server Error");
        errorAttributes.put("message", "An unexpected error occurred");
        errorAttributes.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex,
            HttpServletRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.FORBIDDEN.value());
        errorAttributes.put("error", "Forbidden");
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.FORBIDDEN);
    }

}
