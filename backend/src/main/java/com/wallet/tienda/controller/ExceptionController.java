package com.wallet.tienda.controller;

import com.wallet.tienda.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.management.relation.RoleNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {


    //devuelve excepciones de spring validation
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validException(MethodArgumentNotValidException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errorDetails.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    //devuelve excepciones de datos no encontrados
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({IdNotFoundException.class, RoleNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorDetail> notFoundExceptions(Exception ex) {

        var errorDetails = new ErrorDetail();
        errorDetails.setStatuscode(HttpStatus.NOT_FOUND.value() + " NOT_FOUND");
        errorDetails.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    //devuelve excepciones de datos ya existentes y excepciones generales
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmailExistsException.class, NameExistsException.class, ConfirmPasswordException.class,
            MessagingException.class, ExpiredJwtException.class ,Exception.class})
    public ResponseEntity<ErrorDetail> badRequestExceptions(Exception ex) {

        var errorDetails = new ErrorDetail();
        errorDetails.setStatuscode(HttpStatus.BAD_REQUEST.value() + " BAD_REQUEST");
        errorDetails.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    //devuelve excepciones de credenciales mal ingresadas en el login
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetail> badCredentialsExceptions(BadCredentialsException ex) {

        var errorDetails = new ErrorDetail();
        errorDetails.setStatuscode(HttpStatus.BAD_REQUEST.value() + " BAD_REQUEST");
        errorDetails.setMessage("usuario o contraseña incorrecta. Revise los datos ingresados");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    //devuelve excepciones de formatos mal ingresados
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorDetail> badRequestFormatExceptions() {

        var errorDetails = new ErrorDetail();
        errorDetails.setStatuscode(HttpStatus.BAD_REQUEST.value() + " BAD_REQUEST");
        errorDetails.setMessage("El tipo de formato ingresado es incorrecto");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    //devuelve excepciones de tipeo
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetail> typingErrorExceptions() {

        var errorDetails = new ErrorDetail();
        errorDetails.setStatuscode(HttpStatus.NOT_FOUND.value() + " NOT_FOUND");
        errorDetails.setMessage("Error de tipeo: Revise los datos ingresados.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
}
