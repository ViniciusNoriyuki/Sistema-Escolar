package com.luxfacta.desafio.resources.exceptions;

import com.luxfacta.desafio.services.exceptions.AuthorizationException;
import com.luxfacta.desafio.services.exceptions.DataIntegrityException;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não permitido", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError err = new ValidationError(System.currentTimeMillis(), status.value(), "Erro de validação", e.getMessage(), request.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> accessDenied(AccessDeniedException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Acesso negado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Acesso negado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
