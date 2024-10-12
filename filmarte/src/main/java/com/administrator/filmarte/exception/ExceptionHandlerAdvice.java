/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.exception;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

/**
 *
 * @author ARACELI
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested items is not registered");
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("A database error occurred: " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Method argument not valid: " + e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Request method not supported: " + e.getMessage());
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<?> handleTimeoutException(TimeoutException e) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timed out: " + e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Null pointer encountered: " + e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid argument: " + e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid number format: " + e.getMessage());
    }

}
