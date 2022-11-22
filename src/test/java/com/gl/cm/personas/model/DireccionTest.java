package com.gl.cm.personas.model;


import com.gl.cm.personas.Datos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Direcciones Tests")
class DireccionTest {

    @Test
    @DisplayName("Creacion de Direccion")
    void createDireccionTest() {
        Direccion direccion = new Direccion();
        direccion.setId(UUID.randomUUID());
        direccion.setCalle("Evergreen East");
        direccion.setNumeracion(449);

        Provincia provincia = new Provincia();
        provincia.setNombre("Buenos Aires");
        provincia.setId(UUID.randomUUID());

        direccion.setProvincia(provincia);
        direccion.setPersona(Datos.createPersona1().get());

        assertNotNull(direccion.getId());
        assertNotNull(direccion.getProvincia());
        assertNotNull(direccion.getPersona());
        assertEquals("Evergreen East", direccion.getCalle());
        assertEquals(449, direccion.getNumeracion());
        assertEquals("Buenos Aires", direccion.getProvincia().getNombre());
        assertEquals("Jhon", direccion.getPersona().getNombre());
    }
}
