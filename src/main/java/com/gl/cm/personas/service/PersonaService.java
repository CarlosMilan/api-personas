package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.PersonaNotFoundException;
import com.gl.cm.personas.mapper.PersonaMapper;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Transactional(readOnly = true)
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Persona findById(UUID id) {
        return this.personaRepository.findById(id).orElseThrow(() -> new PersonaNotFoundException("Persona not found"));
    }

    @Transactional
    public Persona savePersona(PersonaDTO personaDTO) {
        Persona persona = personaMapper.toPersona(personaDTO);
        return personaRepository.saveAndFlush(persona);
    }

    @Transactional
    public Persona updatePersona(PersonaDTO personaDTO, UUID id) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new PersonaNotFoundException("Persona not found"));
        persona.setDni(personaDTO.getDni());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setEmail(personaDTO.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona.setFechaNacimiento(LocalDate.parse(personaDTO.getFechaNacimiento(), formatter));
        return personaRepository.saveAndFlush(persona);
    }

    @Transactional
    public void delete(UUID id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
        } else {
            throw new PersonaNotFoundException("Persona Not Found");
        }
    }

}
