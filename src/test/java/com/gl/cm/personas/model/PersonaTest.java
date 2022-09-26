package com.gl.cm.personas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Persona Test")
public class PersonaTest {

    @DisplayName("Creacion persona")
    @Test
    void personaCreationTest(){
        Persona persona = new Persona();
        persona.setEmail("test@correo.com");
        persona.setNombre("Jhon");
        persona.setApellido("Conor");

        String fecha = "20/03/2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona.setDni("20300400");

        assertEquals("test@correo.com", persona.getEmail());
        assertEquals("Jhon", persona.getNombre());
        assertEquals("Conor", persona.getApellido());
        assertEquals("20300400", persona.getDni());
        assertEquals("20/03/2003", formatter.format(persona.getFechaNacimiento()));
        assertNull(persona.getActivo());
        assertNull(persona.getCreacion());
        assertNull(persona.getId());
    }
}
