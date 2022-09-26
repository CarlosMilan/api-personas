package com.gl.cm.personas.exception;

public class PersonaNotFoundException extends RuntimeException{
    public PersonaNotFoundException(String message) {
        super(message);
    }
}
