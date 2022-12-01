package com.gl.cm.personas.service;

import com.gl.cm.personas.Datos;
import com.gl.cm.personas.dto.DireccionDTO;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.ResourceNotFoundException;
import com.gl.cm.personas.model.Direccion;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.model.Provincia;
import com.gl.cm.personas.repository.DireccionesRepository;
import com.gl.cm.personas.repository.PersonaRepository;
import com.gl.cm.personas.repository.ProvinciaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests Direccion Service")
@SpringBootTest
class DireccionServiceTest {

    @MockBean
    private DireccionesRepository direccionesRepository;

    @MockBean
    private PersonaRepository personaRepository;

    @MockBean
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private DireccionesService direccionesService;

    @Test
    @DisplayName("Obtener direcciones por id de persona")
    void getDireccionesByIdPersona(){
        Persona persona = Datos.createPersona1().get();
        when(direccionesRepository.findAllByPersonaId(persona.getId())).thenReturn(Datos.createDireccionList());

        List<DireccionDTO> direcciones = direccionesService.getDirecciones(persona.getId().toString());

        assertNotNull(direcciones);
        assertEquals(2, direcciones.size());
        assertNotNull(direcciones.get(0).getPersona());
        assertEquals("Evergreen East", direcciones.get(0).getCalle());
        assertEquals(449, direcciones.get(0).getNumeracion());
        assertEquals("Buenos Aires", direcciones.get(0).getProvincia().getNombre());
        assertNotNull(direcciones.get(1).getPersona());
        assertEquals("9 de Julio Norte", direcciones.get(1).getCalle());
        assertEquals(2034, direcciones.get(1).getNumeracion());
        assertEquals("Mendoza", direcciones.get(1).getProvincia().getNombre());
        assertNotNull(direcciones.get(0).getPersona());
        assertNotNull(direcciones.get(1).getPersona());
        assertEquals("Jhon", direcciones.get(0).getPersona().getNombre());
        assertEquals("Conor", direcciones.get(0).getPersona().getApellido());
        assertEquals("Jhon", direcciones.get(1).getPersona().getNombre());
        assertEquals("Conor", direcciones.get(1).getPersona().getApellido());

        verify(direccionesRepository).findAllByPersonaId(any());
    }

    @Test
    @DisplayName("Obtener direcciones por personaDTO")
    void getDireccionesByPersonaDTO(){
        PersonaDTO persona = Datos.createPersonaDTO1();
        when(direccionesRepository.findAllByPersonaId(any())).thenReturn(Datos.createDireccionList());

        List<DireccionDTO> direcciones = direccionesService.getDirecciones(persona);

        assertNotNull(direcciones);
        assertEquals(2, direcciones.size());
        assertNotNull(direcciones.get(0).getPersona());
        assertEquals("Evergreen East", direcciones.get(0).getCalle());
        assertEquals(449, direcciones.get(0).getNumeracion());
        assertEquals("Buenos Aires", direcciones.get(0).getProvincia().getNombre());
        assertNotNull(direcciones.get(1).getPersona());
        assertEquals("9 de Julio Norte", direcciones.get(1).getCalle());
        assertEquals(2034, direcciones.get(1).getNumeracion());
        assertEquals("Mendoza", direcciones.get(1).getProvincia().getNombre());
        assertNotNull(direcciones.get(0).getPersona());
        assertNotNull(direcciones.get(1).getPersona());
        assertEquals("Jhon", direcciones.get(0).getPersona().getNombre());
        assertEquals("Conor", direcciones.get(0).getPersona().getApellido());
        assertEquals("Jhon", direcciones.get(1).getPersona().getNombre());
        assertEquals("Conor", direcciones.get(1).getPersona().getApellido());

        verify(direccionesRepository).findAllByPersonaId(any());
    }

    @Test
    @DisplayName("Guardar direccion")
    void guardarDireccionTest() {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        Provincia provincia = new Provincia();
        provincia.setNombre(direccionDTO.getProvincia().getNombre());
        provincia.setId(direccionDTO.getProvincia().getId());

        when(personaRepository.findById(direccionDTO.getPersona().getId())).thenReturn(Datos.createPersona1());
        when(provinciaRepository.findById(direccionDTO.getProvincia().getId())).thenReturn(Optional.of(provincia));
        when(direccionesRepository.save(any())).thenReturn(Datos.createDireccion().get());

        DireccionDTO direccionGuardada = direccionesService.save(direccionDTO);

        assertNotNull(direccionGuardada);
        assertNotNull(direccionGuardada.getPersona());
        assertNotNull(direccionGuardada.getProvincia());
        assertEquals("Evergreen East", direccionGuardada.getCalle());
        assertEquals(449, direccionGuardada.getNumeracion());
        assertEquals("Jhon", direccionGuardada.getPersona().getNombre());
        assertEquals("Buenos Aires", direccionGuardada.getProvincia().getNombre());

        verify(personaRepository).findById(direccionDTO.getPersona().getId());
        verify(provinciaRepository).findById(direccionDTO.getProvincia().getId());
        verify(direccionesRepository).save(any());

    }

    @Test
    @DisplayName("Guardar direccion - Persona no encontrada")
    void guardarDireccionTest2() {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        Provincia provincia = new Provincia();
        provincia.setNombre(direccionDTO.getProvincia().getNombre());
        provincia.setId(direccionDTO.getProvincia().getId());

        when(personaRepository.findById(direccionDTO.getPersona().getId())).thenReturn(Optional.empty());
        when(provinciaRepository.findById(direccionDTO.getProvincia().getId())).thenReturn(Optional.of(provincia));
        when(direccionesRepository.save(any())).thenReturn(Datos.createDireccion());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> direccionesService.save(direccionDTO));

        assertNotNull(ex);
        assertEquals("Persona not found", ex.getMessage());

        verify(personaRepository).findById(direccionDTO.getPersona().getId());
        verify(provinciaRepository, never()).findById(direccionDTO.getProvincia().getId());
        verify(direccionesRepository, never()).save(any());
    }

    @Test
    @DisplayName("Guardar direccion - Provincia no encontrada")
    void guardarDireccionTest3() {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();

        when(personaRepository.findById(direccionDTO.getPersona().getId())).thenReturn(Datos.createPersona1());
        when(provinciaRepository.findById(direccionDTO.getProvincia().getId())).thenReturn(Optional.empty());
        when(direccionesRepository.save(any())).thenReturn(Datos.createDireccion());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> direccionesService.save(direccionDTO));

        assertNotNull(ex);
        assertEquals("Provincia not found", ex.getMessage());

        verify(personaRepository).findById(direccionDTO.getPersona().getId());
        verify(provinciaRepository).findById(direccionDTO.getProvincia().getId());
        verify(direccionesRepository, never()).save(any());
    }

    @Test
    @DisplayName("Editar direccion")
    void editarDireccionTest() {

        when(direccionesRepository.findById(any())).thenReturn(Datos.createDireccion());
        when(direccionesRepository.save(any())).then(invocation -> {
            Direccion d = invocation.getArgument(0);
            d.setCalle("Sarmiento");
            d.setNumeracion(890);
            d.getProvincia().setNombre("Santa Fe");
            return d;
        });

        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        direccionDTO.setCalle("Sarmiento");
        direccionDTO.setNumeracion(890);
        direccionDTO.getProvincia().setNombre("Santa Fe");

        DireccionDTO direccionEditada = direccionesService.update(direccionDTO);

        assertAll(
                () -> assertNotNull(direccionEditada),
                () -> assertNotNull(direccionEditada.getProvincia()),
                () -> assertNotNull(direccionEditada.getPersona()),
                () -> assertEquals("Sarmiento", direccionEditada.getCalle()),
                () -> assertEquals(890, direccionEditada.getNumeracion()),
                () -> assertEquals("Santa Fe", direccionEditada.getProvincia().getNombre()),
                () -> assertEquals("Jhon", direccionEditada.getPersona().getNombre())
        );

        verify(direccionesRepository).findById(direccionDTO.getId());
        verify(direccionesRepository).save(any());
    }

    @Test
    @DisplayName("Editar direccion - Direccion no encontrada")
    void editarDireccionTest2() {

        when(direccionesRepository.findById(any())).thenReturn(Optional.empty());
        when(direccionesRepository.save(any())).then(invocation -> {
            Direccion d = invocation.getArgument(0);
            d.setCalle("Sarmiento");
            d.setNumeracion(890);
            d.getProvincia().setNombre("Santa Fe");
            return d;
        });

        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        direccionDTO.setCalle("Sarmiento");
        direccionDTO.setNumeracion(890);
        direccionDTO.getProvincia().setNombre("Santa Fe");

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> direccionesService.update(direccionDTO));

        assertNotNull(ex);
        assertEquals("Direccion not found", ex.getMessage());

        verify(direccionesRepository).findById(direccionDTO.getId());
        verify(direccionesRepository, never()).save(any());
    }

    @Test
    @DisplayName("Borrar direccion")
    void deleteDireccionTest() {
        DireccionDTO direccionDTO = Datos.createDireccionDTO();
        direccionesService.delete(direccionDTO);

        verify(direccionesRepository).delete(any(Direccion.class));
    }


}
