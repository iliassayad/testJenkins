package net.ayad.rappel.exception;

import jakarta.servlet.http.HttpServletRequest;
import net.ayad.rappel.dto.ApiErrorResponse;
import net.ayad.rappel.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(LocalDateTime.now(), "PRODUCT_NOT_FOUND", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }
}
