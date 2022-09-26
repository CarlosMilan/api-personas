package com.gl.cm.personas.service;

import com.gl.cm.personas.DatosPersonas;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.PersonaNotFoundException;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Persona Service")
@SpringBootTest
public class PersonaServiceTest {

    @MockBean
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaService personaService;

    private DateTimeFormatter formatter;

    @BeforeEach
    void initTests() {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Test
    @DisplayName("Obtener Lista de personas")
    void getPersonas() {
        when(personaRepository.findAll()).thenReturn(DatosPersonas.createPersonasList());

        List<Persona> personas = personaService.getAll();

        assertNotNull(personas);
        assertEquals(3, personas.size());
        assertEquals("Martin", personas.get(1).getNombre());
        assertEquals("pedro@correo.com", personas.get(2).getEmail());
        assertEquals("20/03/2003", formatter.format(personas.get(0).getFechaNacimiento()));

        verify(personaRepository).findAll();
    }

    @Test
    @DisplayName("Obtener persona por Id")
    void getPersonaById() {
        when(personaRepository.findById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002")))
                .thenReturn(DatosPersonas.createPersona1());

        Persona persona = personaService.findById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));

        assertNotNull(persona);
        assertEquals("Jhon", persona.getNombre());
        assertEquals("20300400", persona.getDni());
        assertEquals("20/03/2003", formatter.format(persona.getFechaNacimiento()));
        assertTrue(persona.getActivo());

        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Persona no existe")
    void getPersonaException() {
        UUID uuid = UUID.randomUUID();
        when(personaRepository.findById(any())).thenReturn(Optional.empty());
        PersonaNotFoundException ex = assertThrows(PersonaNotFoundException.class, () -> personaService.findById(uuid));

        assertEquals("Persona not found", ex.getMessage());
        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Guardar Persona")
    void savePersona() {
        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();

        when(personaRepository.saveAndFlush(any())).thenReturn(DatosPersonas.createPersona1().get());

        Persona persona = personaService.savePersona(personaDTO);

        assertAll(
                () -> assertNotNull(persona),
                () -> assertEquals(personaDTO.getApellido(), persona.getApellido()),
                () -> assertEquals(personaDTO.getDni(), persona.getDni()),
                () -> assertEquals(personaDTO.getFechaNacimiento(), persona.getFechaNacimiento()),
                () -> assertEquals(personaDTO.getEmail(), persona.getEmail())
        );

        verify(personaRepository).saveAndFlush(any(Persona.class));

    }

    @Test
    @DisplayName("Editar Persona")
    void updatePersona() {
        when(personaRepository.findById(any())).thenReturn(DatosPersonas.createPersona1());
        when(personaRepository.saveAndFlush(any(Persona.class))).then(invocation -> {
            Persona p = invocation.getArgument(0);
            p.setEmail("test01@email.com");
            return p;
        });

        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();
        personaDTO.setEmail("test01@email.com");
        Persona persona = personaService.updatePersona(personaDTO, UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));

        assertAll(
                () -> assertNotNull(persona),
                () -> assertEquals(personaDTO.getApellido(), persona.getApellido()),
                () -> assertEquals(personaDTO.getDni(), persona.getDni()),
                () -> assertEquals(personaDTO.getFechaNacimiento(), persona.getFechaNacimiento()),
                () -> assertEquals(personaDTO.getEmail(), persona.getEmail())
        );
        verify(personaRepository).saveAndFlush(any());
        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Editar Persona Not Found")
    void updatePersonaNotFound() {
        when(personaRepository.findById(any())).thenReturn(Optional.empty());
        when(personaRepository.saveAndFlush(any(Persona.class))).then(invocation -> {
            Persona p = invocation.getArgument(0);
            p.setEmail("test01@email.com");
            return p;
        });

        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();
        personaDTO.setEmail("test01@email.com");
        PersonaNotFoundException ex = assertThrows(PersonaNotFoundException.class, () ->
                personaService.updatePersona(personaDTO, UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002")));

        assertEquals("Persona not found", ex.getMessage());

        verify(personaRepository, never()).saveAndFlush(any());
        verify(personaRepository).findById(any());
    }

}
