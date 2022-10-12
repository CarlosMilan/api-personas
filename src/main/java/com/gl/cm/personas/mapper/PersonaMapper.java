package com.gl.cm.personas.mapper;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersonaMapper {

    private final ModelMapper mapper;

    @Autowired
    public PersonaMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public Persona toPersona(PersonaDTO personaDTO) {
        return mapper.map(personaDTO, Persona.class);
    }

    public Persona personaDTOtoPersona(PersonaDTO personaDTO, Persona persona) {
        mapper.map(personaDTO, persona);
        return persona;
    }

    public PersonaDTO toPersonaDTO(Persona persona) {
        return mapper.map(persona, PersonaDTO.class);
    }
}
