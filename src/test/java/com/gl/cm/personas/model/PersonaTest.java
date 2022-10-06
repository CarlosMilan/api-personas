package com.gl.cm.personas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Persona Test")
class PersonaTest {

    @DisplayName("Creacion persona")
    @Test
    void personaCreationTest(){
        Persona persona = new Persona();
        persona.setId(UUID.randomUUID());
        persona.setEmail("test@correo.com");
        persona.setNombre("Jhon");
        persona.setApellido("Conor");

        String fecha = "20-03-2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        persona.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona.setDni("20300400");
        List<Direccion> direcciones = new ArrayList<>();
        Direccion direccion = new Direccion();
        direccion.setId(UUID.randomUUID());
        direccion.setPersona(persona);
        direccion.setCalle("Evergreen W");
        direccion.setNumeracion(123);
        direcciones.add(direccion);

        persona.setDirecciones(direcciones);

        assertEquals("test@correo.com", persona.getEmail());
        assertEquals("Jhon", persona.getNombre());
        assertEquals("Conor", persona.getApellido());
        assertEquals("20300400", persona.getDni());
        assertEquals("20-03-2003", formatter.format(persona.getFechaNacimiento()));
        assertEquals("Evergreen W", persona.getDirecciones().get(0).getCalle());
        assertEquals(123, persona.getDirecciones().get(0).getNumeracion());
        assertEquals(persona, persona.getDirecciones().get(0).getPersona());
        assertNotNull(persona.getDirecciones().get(0).getId());
        assertNull(persona.getActivo());
        assertNull(persona.getCreacion());
        assertNotNull(persona.getId());
    }

    @Test
    @DisplayName("Metodo Pre persist")
    void prePersistTest() {
        Persona persona = new Persona();
        persona.prePersist();

        assertEquals(true, persona.getActivo());
        assertNotNull(persona.getCreacion());

    }
}
