package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.DireccionDTO;
import com.gl.cm.personas.dto.PersonaDTO;

import java.util.List;

public interface DireccionesService {
    DireccionDTO save(DireccionDTO direccionDTO);
    List<DireccionDTO> getDirecciones(PersonaDTO personaDTO);
    List<DireccionDTO> getDirecciones(String idPersona);
    DireccionDTO update(DireccionDTO direccionDTO);
    void delete(DireccionDTO direccionDTO);
}
