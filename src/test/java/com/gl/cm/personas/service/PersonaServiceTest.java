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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Persona Service")
@SpringBootTest
class PersonaServiceTest {

    @MockBean
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaService personaService;

    @Test
    @DisplayName("Obtener Lista de personas")
    void getPersonas() {
        when(personaRepository.findAll()).thenReturn(DatosPersonas.createPersonasList());

        List<PersonaDTO> personas = personaService.getAll();

        assertNotNull(personas);
        assertEquals(3, personas.size());
        assertEquals("Martin", personas.get(1).getNombre());
        assertEquals("pedro@correo.com", personas.get(2).getEmail());
        assertEquals("20-03-2003", personas.get(0).getFechaNacimiento());

        verify(personaRepository).findAll();
    }

    @Test
    @DisplayName("Obtener persona por Id")
    void getPersonaById() {
        when(personaRepository.findById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002")))
                .thenReturn(DatosPersonas.createPersona1());

        PersonaDTO persona = personaService.findById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));

        assertNotNull(persona);
        assertEquals("Jhon", persona.getNombre());
        assertEquals("20300400", persona.getDni());
        assertEquals("20-03-2003", persona.getFechaNacimiento());

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

        PersonaDTO persona = personaService.savePersona(personaDTO);

        assertAll(
                () -> assertNotNull(persona),
                () -> assertEquals(personaDTO.getApellido(), persona.getApellido()),
                () -> assertEquals(personaDTO.getDni(), persona.getDni()),
                () -> assertEquals(personaDTO.getEmail(), persona.getEmail())
        );

        verify(personaRepository).saveAndFlush(any(Persona.class));

    }

    @Test
    @DisplayName("Editar Persona")
    void updatePersona() {
        when(personaRepository.existsById(any())).thenReturn(true);
        when(personaRepository.saveAndFlush(any(Persona.class))).then(invocation -> {
            Persona p = invocation.getArgument(0);
            p.setEmail("test01@email.com");
            return p;
        });

        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();
        personaDTO.setEmail("test01@email.com");
        PersonaDTO persona = personaService.updatePersona(personaDTO);

        assertAll(
                () -> assertNotNull(persona),
                () -> assertEquals(personaDTO.getApellido(), persona.getApellido()),
                () -> assertEquals(personaDTO.getDni(), persona.getDni()),
                () -> assertEquals(personaDTO.getEmail(), persona.getEmail())
        );
        verify(personaRepository).saveAndFlush(any());
        verify(personaRepository).existsById(any());
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
                personaService.updatePersona(personaDTO));

        assertEquals("Persona not found", ex.getMessage());

        verify(personaRepository, never()).saveAndFlush(any());
        verify(personaRepository).existsById(any());
    }

    @Test
    @DisplayName("Borrar persona")
    void deletePersona() {
        when(personaRepository.existsById(any())).thenReturn(true);

        personaService.delete(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        verify(personaRepository).deleteById(any());
    }

    @Test
    @DisplayName("Borrar persona No existente")
    void deletePersonaNoExiste() {
        when(personaRepository.existsById(any())).thenReturn(false);

        PersonaNotFoundException ex = assertThrows(PersonaNotFoundException.class, () ->
                personaService.delete(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002")));

        assertEquals("Persona Not Found", ex.getMessage());
        verify(personaRepository).existsById(any());
        verify(personaRepository, never()).deleteById(any());
    }


}
