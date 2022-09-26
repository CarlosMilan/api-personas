package com.gl.cm.personas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.cm.personas.DatosPersonas;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.repository.PersonaRepository;
import com.gl.cm.personas.service.PersonaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.hamcrest.Matchers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Persona Controller Test")
@WebMvcTest(controllers = PersonaController.class)
public class PersonaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonaService personaService;

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
        when(personaService.getAll()).thenReturn(DatosPersonas.createPersonasList());

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
        when(personaService.findById(any())).thenReturn(DatosPersonas.createPersona1().get());

        mvc.perform(get("/personas/c2654c34-3dad-11ed-b878-0242ac120002").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Jhon"));

        verify(personaService).findById(any());
    }

    @Test
    @DisplayName("Guardar Persona")
    void savePersona() throws Exception {
        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();
        when(personaService.savePersona(any())).thenReturn(DatosPersonas.createPersona1().get());

        mvc.perform(post("/personas").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(personaDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Jhon"));
    }

    @Test
    @DisplayName("Editar Persona")
    void updatePersona() throws Exception {
        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();
        when(personaService.updatePersona(any(), any())).thenReturn(DatosPersonas.createPersona1().get());

        mvc.perform(put("/personas/c2654c34-3dad-11ed-b878-0242ac120002").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(personaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Jhon"));
    }

    @Test
    @DisplayName("Borrar Persona")
    void deletePersona() throws Exception {
        PersonaDTO personaDTO = DatosPersonas.createPersonaDTO1();
        when(personaRepository.existsById(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"))).thenReturn(true);

        mvc.perform(delete("/personas/c2654c34-3dad-11ed-b878-0242ac120002"))
                .andExpect(status().isOk());
    }

}
