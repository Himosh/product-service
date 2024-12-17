package com.mini_project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

@Slf4j
public abstract class AbstractBaseController {

    // Method to handle paginated success responses
    protected <T> ResponseEntity<Page<T>> handlePaginatedResponse(Supplier<Page<T>> supplier, String logMessage) {
        try {
            log.info(logMessage);
            Page<T> result = supplier.get();
            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Page.empty());
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred: {}", logMessage, e);
            throw new RuntimeException("Error occurred: " + e.getMessage(), e);
        }
    }

    // Method to handle single entity retrieval
    protected <T> ResponseEntity<T> handleSingleResponse(Supplier<T> supplier, String logMessage) {
        try {
            log.info(logMessage);
            T result = supplier.get();
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            log.warn("Entity not found: {}", logMessage, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error occurred: {}", logMessage, e);
            throw new RuntimeException("Error occurred: " + e.getMessage(), e);
        }
    }

    // Method to handle non-paginated success responses
    protected <T> ResponseEntity<T> handleCustomResponse(Supplier<T> supplier, HttpStatus successStatus, String logMessage) {
        try {
            log.info(logMessage);
            T result = supplier.get();
            return new ResponseEntity<>(result, successStatus);
        } catch (Exception e) {
            log.error("Operation failed: {}", logMessage, e);
            throw new RuntimeException("Operation failed: " + e.getMessage(), e);
        }
    }
}
