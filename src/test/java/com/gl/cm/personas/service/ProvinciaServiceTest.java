package com.gl.cm.personas.service;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.ProvinciaDTO;
import com.gl.cm.personas.repository.ProvinciaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Provincia Service")
@SpringBootTest
class ProvinciaServiceTest {

    @MockBean
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProvinciaService provinciaService;

    @Test
    @DisplayName("Obteniendo Todas las provincias")
    void getProvinciasTest() {
        when(provinciaRepository.findAll()).thenReturn(Datos.createProvinciaList());

        List<ProvinciaDTO> provincias = provinciaService.getAll();

        assertNotNull(provincias);
        assertEquals(3, provincias.size());
        assertEquals("Buenos Aires", provincias.get(0).getNombre());
        assertEquals("Jujuy", provincias.get(2).getNombre());
        assertEquals("Mendoza", provincias.get(1).getNombre());

        verify(provinciaRepository).findAll();
    }
}
