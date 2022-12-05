package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.PersonaDTO;

import java.util.List;
import java.util.UUID;

public interface PersonaService {
    List<PersonaDTO> getAll();
    PersonaDTO findById(UUID id);
    PersonaDTO savePersona(PersonaDTO personaDTO);
    PersonaDTO updatePersona(PersonaDTO personaDTO);
    void delete(PersonaDTO personaDTO);
}
