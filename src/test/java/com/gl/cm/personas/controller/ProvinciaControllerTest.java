package com.gl.cm.personas.controller;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.service.ProvinciaServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Provincia Controller")
@WebMvcTest(controllers = ProvinciaController.class)
class ProvinciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProvinciaServiceImpl provinciaService;

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
