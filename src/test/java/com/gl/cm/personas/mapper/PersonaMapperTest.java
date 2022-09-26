package com.gl.cm.personas.mapper;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Persona Mapper")
public class PersonaMapperTest {
    private PersonaMapper personaMapper;
    private DateTimeFormatter formatter;

    @BeforeEach
    void initTests() {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.personaMapper = new PersonaMapper();
    }

    @DisplayName("Creacion PersonaDTO")
    @Test
    void personaDTOCreationTest() {

        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setEmail("test@correo.com");
        personaDTO.setNombre("Jhon");
        personaDTO.setApellido("Conor");

        personaDTO.setFechaNacimiento("20/03/2003");

        personaDTO.setDni("20300400");

        assertEquals("test@correo.com", personaDTO.getEmail());
        assertEquals("Jhon", personaDTO.getNombre());
        assertEquals("Conor", personaDTO.getApellido());
        assertEquals("20300400", personaDTO.getDni());
        assertEquals("20/03/2003", personaDTO.getFechaNacimiento());

    }

    @DisplayName("PersonaDTO a Persona")
    @Test
    void personaDTOAPersonaTest(){
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setEmail("test@correo.com");
        personaDTO.setNombre("Jhon");
        personaDTO.setApellido("Conor");

        personaDTO.setFechaNacimiento("20/03/2003");

        personaDTO.setDni("20300400");

        Persona persona = personaMapper.toPersona(personaDTO);

        assertEquals("test@correo.com", persona.getEmail());
        assertEquals("Jhon", persona.getNombre());
        assertEquals("Conor", persona.getApellido());
        assertEquals("20300400", persona.getDni());
        assertEquals("20/03/2003", formatter.format(persona.getFechaNacimiento()));
        assertNull(persona.getActivo());
        assertNull(persona.getCreacion());
        assertNull(persona.getId());
    }

    @DisplayName("Persona a PersonaDTO")
    @Test
    void personaTOPersonaDTO(){
        Persona persona = new Persona();
        persona.setEmail("test@correo.com");
        persona.setNombre("Jhon");
        persona.setApellido("Conor");

        String fecha = "20/03/2003";
        persona.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona.setDni("20300400");

        PersonaDTO personaDTO = personaMapper.toPersonaDTO(persona);

        assertEquals("test@correo.com", personaDTO.getEmail());
        assertEquals("Jhon", personaDTO.getNombre());
        assertEquals("Conor", personaDTO.getApellido());
        assertEquals("20300400", personaDTO.getDni());
        assertEquals("20/03/2003", formatter.format(persona.getFechaNacimiento()));
    }

}
