package com.gl.cm.personas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Provincia Tests")
class ProvinciaTest {

    @Test
    @DisplayName("Creacion de provincia")
    void createProvinciasTest() {
        Provincia provincia = new Provincia();
        provincia.setId(UUID.randomUUID());
        provincia.setNombre("Buenos Aires");

        assertNotNull(provincia.getId());
        assertEquals("Buenos Aires", provincia.getNombre());
    }
}
