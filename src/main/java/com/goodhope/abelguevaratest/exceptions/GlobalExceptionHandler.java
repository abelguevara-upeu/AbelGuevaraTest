package com.goodhope.abelguevaratest.exceptions;

import com.goodhope.abelguevaratest.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para la aplicación.
 * <p>
 * Esta clase intercepta las excepciones lanzadas en los controladores y retorna respuestas
 * estandarizadas en formato {@link ApiResponse} junto con el código de estado HTTP correspondiente.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Manejo del JSON mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleJsonParseException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Error en el formato JSON", ex.getMessage())
        );
    }

    //Manejo de validaciones de @Valid en request body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Errores en la validación", errors)
        );
    }

    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error inesperado", ex.getMessage())
        );
    }
}
