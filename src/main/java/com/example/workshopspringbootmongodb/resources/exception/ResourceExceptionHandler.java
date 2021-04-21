package com.example.workshopspringbootmongodb.resources.exception;

import com.example.workshopspringbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    private static final String NAO_ENCONTRADO = "Não encontrado";

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError se = new StandardError(System.currentTimeMillis(), status.value(), NAO_ENCONTRADO, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(se);
    }

}
