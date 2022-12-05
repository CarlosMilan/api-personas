package com.gl.cm.personas.controller;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.service.ProvinciaServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Provincia Controller")
class ProvinciaControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private ProvinciaController provinciaController;

    @Mock
    private ProvinciaServiceImpl provinciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(provinciaController).build();
    }
    @Test
    @DisplayName("Obtener todas las provincias")
    void getProvinciasTest() throws Exception{
        when(provinciaService.getAll()).thenReturn(Datos.createProvinciaDTOList());
        mvc.perform(get("/provincias").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].nombre").value("Buenos Aires"))
                .andExpect(jsonPath("$[1].nombre").value("Mendoza"))
                .andExpect(jsonPath("$[2].nombre").value("Jujuy"));
    }
}
