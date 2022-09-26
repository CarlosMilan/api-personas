package com.gl.cm.personas.mapper;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public Persona toPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setDni(personaDTO.getDni());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setEmail(personaDTO.getEmail());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());

        return persona;
    }

    public PersonaDTO toPersonaDTO(Persona persona) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setDni(persona.getDni());
        personaDTO.setApellido(persona.getApellido());
        personaDTO.setEmail(persona.getEmail());
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setFechaNacimiento(persona.getFechaNacimiento());
        return personaDTO;
    }
}
