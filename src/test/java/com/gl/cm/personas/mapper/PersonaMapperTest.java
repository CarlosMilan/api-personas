package com.gl.cm.personas.mapper;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.ErrorDTO;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Persona Mapper")
class PersonaMapperTest {
    private PersonaMapper personaMapper;
    private DateTimeFormatter formatter;

    @BeforeEach
    void initTests() {
        this.personaMapper = new PersonaMapper(new ModelMapper());
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @DisplayName("Creacion PersonaDTO")
    @Test
    void personaDTOCreationTest() {

        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setEmail("test@correo.com");
        personaDTO.setNombre("Jhon");
        personaDTO.setApellido("Conor");

        personaDTO.setFechaNacimiento("20-03-2003");
        personaDTO.setDni("20300400");

        assertEquals("test@correo.com", personaDTO.getEmail());
        assertEquals("Jhon", personaDTO.getNombre());
        assertEquals("Conor", personaDTO.getApellido());
        assertEquals("20300400", personaDTO.getDni());
        assertEquals("20-03-2003", personaDTO.getFechaNacimiento());

    }

    @Test
    @DisplayName("Creacion ErrorDTO")
    void errorDTOCreacionTest() {
        ErrorDTO errorDTO = new ErrorDTO(400, "Persona Not Found");

        assertEquals(400, errorDTO.getCodigo());
        assertEquals("Persona Not Found", errorDTO.getMensaje());
        assertNotNull(errorDTO.getFecha());

        errorDTO.setCodigo(500);
        errorDTO.setMensaje("Internal Server Error");

        errorDTO.setFecha(LocalDateTime.now());

        assertEquals("Internal Server Error", errorDTO.getMensaje());
        assertEquals(500, errorDTO.getCodigo());
        assertNotNull(errorDTO.getFecha());

    }

    @DisplayName("PersonaDTO a Persona")
    @Test
    void personaDTOAPersonaTest(){
        PersonaDTO personaDTO = Datos.createPersonaDTO1();

        Persona persona = personaMapper.toPersona(personaDTO);

        assertNotNull(persona.getId());
        assertEquals("test@correo.com", persona.getEmail());
        assertEquals("Jhon", persona.getNombre());
        assertEquals("Conor", persona.getApellido());
        assertEquals("20300400", persona.getDni());
        assertNull(persona.getActivo());
        assertNull(persona.getCreacion());
    }

    @DisplayName("Persona a PersonaDTO")
    @Test
    void personaTOPersonaDTO(){
        Persona persona = Datos.createPersona1().get();
        System.out.println("persona.getFechaNacimiento() = " + persona.getFechaNacimiento());

        PersonaDTO personaDTO = personaMapper.toPersonaDTO(persona);

        assertEquals(persona.getId(), personaDTO.getId());
        assertEquals("test@correo.com", personaDTO.getEmail());
        assertEquals("Jhon", personaDTO.getNombre());
        assertEquals("Conor", personaDTO.getApellido());
        assertEquals("20300400", personaDTO.getDni());
        assertEquals("20-03-2003", formatter.format(persona.getFechaNacimiento()));
    }

}
