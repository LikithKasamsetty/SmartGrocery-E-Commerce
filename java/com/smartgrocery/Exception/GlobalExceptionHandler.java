package com.smartgrocery.Exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: product id not found
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseErrorHanduler> handleNotFound(ProductNotFoundException ex,
                                                   HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
    }

    // 400: @Valid failed on a request body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorHanduler> handleValidation(MethodArgumentNotValidException ex,
                                                     HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        return build(HttpStatus.BAD_REQUEST, "Validation failed", request, errors);
    }

    // 400: malformed JSON, or wrong type such as "price": "abc"
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseErrorHanduler> handleUnreadable(HttpMessageNotReadableException ex,
                                                     HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Malformed or unreadable JSON request body", request, null);
    }

    // 400: wrong type in the URL, such as /products/abc
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseErrorHanduler> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                       HttpServletRequest request) {
        String message = "Invalid value '" + ex.getValue() + "' for parameter '" + ex.getName() + "'";
        return build(HttpStatus.BAD_REQUEST, message, request, null);
    }

    // 400: our own checks in the service (bulk safety net)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseErrorHanduler> handleIllegalArgument(IllegalArgumentException ex,
                                                          HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
    }

    // Catch-all: anything unexpected becomes a clean 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorHanduler> handleAny(Exception ex, HttpServletRequest request) {

        // Spring's own web errors (unknown URL, wrong method, missing parameter,
        // wrong content type) already know their status code. Keep it.
        if (ex instanceof ErrorResponse er) {
            HttpStatus status = HttpStatus.valueOf(er.getStatusCode().value());
            return build(status, er.getBody().getDetail(), request, null);
        }

        ex.printStackTrace();   // temporary: shows the real cause in the console
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong on the server", request, null);
    }

    private ResponseEntity<ResponseErrorHanduler> build(HttpStatus status, String message,
                                           HttpServletRequest request,
                                           Map<String, String> validationErrors) {
    	ResponseErrorHanduler body = new ResponseErrorHanduler(status.value(), status.getReasonPhrase(),
                message, request.getRequestURI(), validationErrors);
        return ResponseEntity.status(status).body(body);
    }
}
