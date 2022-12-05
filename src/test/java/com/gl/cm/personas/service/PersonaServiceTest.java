package com.gl.cm.personas.service;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.ResourceNotFoundException;
import com.gl.cm.personas.mapper.PersonaMapper;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.repository.PersonaRepository;
import com.gl.cm.personas.repository.ProvinciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Persona Service")
@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private ProvinciaRepository provinciaRepository;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @BeforeEach
    void setUp() {
        personaService = new PersonaServiceImpl(personaRepository, provinciaRepository, new PersonaMapper(new ModelMapper()));
    }

    @Test
    @DisplayName("Obtener Lista de personas")
    void getPersonas() {
        when(personaRepository.findAll()).thenReturn(Datos.createPersonasList());

        List<PersonaDTO> personas = personaService.getAll();

        assertNotNull(personas);
        assertEquals(3, personas.size());
        assertEquals("Martin", personas.get(1).getNombre());
        assertEquals("pedro@correo.com", personas.get(2).getEmail());
        assertNotNull(personas.get(0).getFechaNacimiento());

        verify(personaRepository).findAll();
    }

    @Test
    @DisplayName("Obtener persona por Id")
    void getPersonaById() {
        when(personaRepository.findById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002")))
                .thenReturn(Datos.createPersona1());

        PersonaDTO persona = personaService.findById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));

        assertNotNull(persona);
        assertEquals("Jhon", persona.getNombre());
        assertEquals("20300400", persona.getDni());
        assertNotNull(persona.getFechaNacimiento());

        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Persona no existe")
    void getPersonaException() {
        UUID uuid = UUID.randomUUID();
        when(personaRepository.findById(any())).thenReturn(Optional.empty());
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> personaService.findById(uuid));

        assertEquals("Persona not found", ex.getMessage());
        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Guardar Persona")
    void savePersona() {
        PersonaDTO personaDTO = Datos.createPersonaDTO1();

        when(personaRepository.save(any())).thenReturn(Datos.createPersona1().get());

        PersonaDTO persona = personaService.savePersona(personaDTO);

        assertAll(
                () -> assertNotNull(persona),
                () -> assertEquals(personaDTO.getApellido(), persona.getApellido()),
                () -> assertEquals(personaDTO.getDni(), persona.getDni()),
                () -> assertEquals(personaDTO.getEmail(), persona.getEmail())
        );

        verify(personaRepository).save(any(Persona.class));

    }

    @Test
    @DisplayName("Editar Persona")
    void updatePersona() {
        when(personaRepository.findById(any())).thenReturn(Datos.createPersona1());
        when(personaRepository.saveAndFlush(any(Persona.class))).then(invocation -> {
            Persona p = invocation.getArgument(0);
            p.setEmail("test01@email.com");
            return p;
        });

        PersonaDTO personaDTO = Datos.createPersonaDTO1();
        personaDTO.setEmail("test01@email.com");
        PersonaDTO persona = personaService.updatePersona(personaDTO);

        assertAll(
                () -> assertNotNull(persona),
                () -> assertEquals(personaDTO.getApellido(), persona.getApellido()),
                () -> assertEquals(personaDTO.getDni(), persona.getDni()),
                () -> assertEquals(personaDTO.getEmail(), persona.getEmail())
        );
        verify(personaRepository).saveAndFlush(any());
        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Editar Persona Not Found")
    void updatePersonaNotFound() {
        when(personaRepository.findById(any())).thenReturn(Optional.empty());

        PersonaDTO personaDTO = Datos.createPersonaDTO1();
        personaDTO.setEmail("test01@email.com");
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                personaService.updatePersona(personaDTO));

        assertEquals("Persona not found", ex.getMessage());

        verify(personaRepository, never()).saveAndFlush(any());
        verify(personaRepository).findById(any());
    }

    @Test
    @DisplayName("Borrar persona")
    void deletePersona() {
        PersonaDTO personaDTO = Datos.createPersonaDTO1();
        personaService.delete(personaDTO);
        verify(personaRepository).delete(any());
    }


}
