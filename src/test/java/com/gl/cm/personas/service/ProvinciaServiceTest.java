package com.gl.cm.personas.service;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.ProvinciaDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Provincia Service")
@ExtendWith(MockitoExtension.class)
class ProvinciaServiceTest {

    @Mock
    private ProvinciaRepository provinciaRepository;

    @InjectMocks
    private ProvinciaServiceImpl provinciaService;

    @BeforeEach
    void setUp() {
        provinciaService = new ProvinciaServiceImpl(provinciaRepository, new ModelMapper());
    }
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
