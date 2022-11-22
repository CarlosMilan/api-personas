package com.gl.cm.personas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.ResourceNotFoundException;
import com.gl.cm.personas.repository.PersonaRepository;
import com.gl.cm.personas.service.PersonaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.hamcrest.Matchers;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Persona Controller Test")
@WebMvcTest(controllers = PersonaController.class)
class PersonaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonaServiceImpl personaService;

    @MockBean
    private PersonaRepository personaRepository;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
    }


    @Test
    @DisplayName("Lista de Personas")
    void getPersonas() throws Exception {
        when(personaService.getAll()).thenReturn(Datos.createPersonasDTOList());

        mvc.perform(get("/personas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].nombre").value("Jhon"))
                .andExpect(jsonPath("$[1].email").value("martin@correo.com"))
                .andExpect(jsonPath("$[2].dni").value("20300400"));
    }


    @Test
    @DisplayName("Obtener Personas")
    void getPersona() throws Exception {
        when(personaService.findById(any())).thenReturn(Datos.createPersonaDTO1());

        mvc.perform(get("/personas/c2654c34-3dad-11ed-b878-0242ac120002").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Jhon"));

        verify(personaService).findById(any());
    }

    @Test
    @DisplayName("Obtener persona No existente")
    void getPersonaNotFound() throws Exception{
        when(personaService.findById(any())).thenThrow(new ResourceNotFoundException("Persona Not Found"));

        mvc.perform(get("/personas/c2654c34-3dad-11ed-b878-0242ac120002").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensaje").value("Persona Not Found"));
    }

    @Test
    @DisplayName("Obtener persona Lanza Exception")
    void getPersonaThrowException() throws Exception{

        mvc.perform(get("/personas/c2654c34").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(500))
                .andExpect(jsonPath("$.mensaje").value("Invalid UUID string: c2654c34"));
    }

    @Test
    @DisplayName("Guardar Persona")
    void savePersona() throws Exception {
        PersonaDTO personaDTO = Datos.createPersonaDTO1();
        when(personaService.savePersona(any())).thenReturn(Datos.createPersonaDTO1());

        mvc.perform(post("/personas").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(personaDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Jhon"));
    }

    @Test
    @DisplayName("Editar Persona")
    void updatePersona() throws Exception {
        PersonaDTO personaDTO = Datos.createPersonaDTO1();
        when(personaService.updatePersona(any())).thenReturn(Datos.createPersonaDTO1());

        mvc.perform(put("/personas").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(personaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Jhon"));
    }

    @Test
    @DisplayName("Actualizar Persona no existente")
    void updatePersonaNotFound() throws Exception{
        PersonaDTO personaDTO = Datos.createPersonaDTO1();
        when(personaService.updatePersona(any())).thenThrow(new ResourceNotFoundException("Persona not found"));

        mvc.perform(put("/personas").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(personaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensaje").value("Persona not found"));
    }

    @Test
    @DisplayName("Borrar Persona")
    void deletePersona() throws Exception {
        when(personaRepository.existsById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"))).thenReturn(true);

        mvc.perform(delete("/personas/c2654c34-3dad-11ed-b878-0242ac120002"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Borrar Persona no existente")
    void deletePersonaNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Persona not found"))
                .when(personaService).delete(any());

        mvc.perform(delete("/personas/c2654c34-3dad-11ed-b878-0242ac120002"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensaje").value("Persona not found"));
    }

}
