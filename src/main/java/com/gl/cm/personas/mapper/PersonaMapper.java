package com.gl.cm.personas.mapper;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PersonaMapper {

    public Persona toPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setDni(personaDTO.getDni());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setEmail(personaDTO.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona.setFechaNacimiento(LocalDate.parse(personaDTO.getFechaNacimiento(), formatter));

        return persona;
    }

    public PersonaDTO toPersonaDTO(Persona persona) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setDni(persona.getDni());
        personaDTO.setApellido(persona.getApellido());
        personaDTO.setEmail(persona.getEmail());
        personaDTO.setNombre(persona.getNombre());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personaDTO.setFechaNacimiento(formatter.format(persona.getFechaNacimiento()));
        return personaDTO;
    }
}
