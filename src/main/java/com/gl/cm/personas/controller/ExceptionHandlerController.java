package com.gl.cm.personas.controller;

import com.gl.cm.personas.dto.ErrorDTO;
import com.gl.cm.personas.exception.PersonaNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(Exception ex) {
        ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        log.error("exceptionHandler - Se ha producido una excepcion");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<ErrorDTO> personaNotFoundExceptionHandler(PersonaNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        log.error("personaNotFoundExceptionHandler - No se encontro persona");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
