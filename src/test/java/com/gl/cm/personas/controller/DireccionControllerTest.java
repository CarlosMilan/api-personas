package com.gl.cm.personas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.DireccionDTO;
import com.gl.cm.personas.exception.ResourceNotFoundException;
import com.gl.cm.personas.repository.DireccionesRepository;
import com.gl.cm.personas.service.DireccionesServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Direccion Controller")
@WebMvcTest(controllers = DireccionController.class)
class DireccionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DireccionesServiceImpl direccionesServices;

    @MockBean
    private DireccionesRepository direccionesRepository;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Obtener direcciones por id persona")
    void getDireccionesByIdPersonaTest() throws Exception {

        when(direccionesServices.getDirecciones(any(String.class))).thenReturn(Datos.createDireccionDTOList());

        mvc.perform(get("/direcciones/c2654c34-3dad-11ed-b878-0242ac120002").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].calle").value("Evergreen East"))
                .andExpect(jsonPath("$[0].numeracion").value(449))
                .andExpect(jsonPath("$[0].provincia.nombre").value("Buenos Aires"))
                .andExpect(jsonPath("$[0].persona.nombre").value("Jhon"))
                .andExpect(jsonPath("$[1].calle").value("9 de Julio Norte"))
                .andExpect(jsonPath("$[1].numeracion").value(2034))
                .andExpect(jsonPath("$[1].provincia.nombre").value("Mendoza"))
                .andExpect(jsonPath("$[1].persona.apellido").value("Conor"));
    }

    @Test
    @DisplayName("Obtener direcciones por id persona - No tiene direcciones")
    void getDireccionesByIdPersonaTest2() throws Exception {

        when(direccionesServices.getDirecciones(any(String.class))).thenReturn(Collections.emptyList());

        mvc.perform(get("/direcciones/c2654c34-3dad-11ed-b878-0242ac120002").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    @DisplayName("Guardar Direccion")
    void guardarDireccionTest1() throws Exception {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        when(direccionesServices.save(direccionDTO)).thenReturn(direccionDTO);

        mvc.perform(post("/direcciones").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(direccionDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.calle").value("Evergreen East"))
                .andExpect(jsonPath("$.numeracion").value(449))
                .andExpect(jsonPath("$.provincia.nombre").value("Buenos Aires"))
                .andExpect(jsonPath("$.persona.nombre").value("Jhon"));
    }

    @Test
    @DisplayName("Guardar Direccion - Excepcion")
    void guardarDireccionTest2() throws Exception {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        when(direccionesServices.save(direccionDTO)).thenThrow(new ResourceNotFoundException("Persona not found"));

        mvc.perform(post("/direcciones").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(direccionDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensaje").value("Persona not found"));
    }

    @Test
    @DisplayName("Actualizar Direccion")
    void actualizarDireccionTest1() throws Exception {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        when(direccionesServices.update(any())).thenReturn(direccionDTO);

        mvc.perform(put("/direcciones").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(direccionDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.calle").value("Evergreen East"))
                .andExpect(jsonPath("$.provincia.nombre").value("Buenos Aires"))
                .andExpect(jsonPath("$.persona.nombre").value("Jhon"));
    }

    @Test
    @DisplayName("Actualizar Direccion - Excepcion")
    void actualizarDireccionTest2() throws Exception {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        when(direccionesServices.update(any())).thenThrow(new ResourceNotFoundException("Direccion not found"));

        mvc.perform(put("/direcciones").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(direccionDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensaje").value("Direccion not found"));
    }

    @Test
    @DisplayName("Borrar direccion")
    void borrarDireccionTest() throws Exception {
        String idDireccion = "c2654c34-3dad-11ed-b878-0242ac120002";
        when(direccionesRepository.existsById(UUID.fromString(idDireccion))).thenReturn(true);

        mvc.perform(delete("/direcciones/"+idDireccion))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Borrar direccion no existe")
    void borrarDireccionTest2() throws Exception {
        String idDireccion = "c2654c34-3dad-11ed-b878-0242ac120002";
        doThrow(new ResourceNotFoundException("Direccion not found"))
                .when(direccionesServices).delete(idDireccion);

        mvc.perform(delete("/direcciones/"+idDireccion))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensaje").value("Direccion not found"));
    }
}
