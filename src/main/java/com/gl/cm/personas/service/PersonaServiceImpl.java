package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.PersonaNotFoundException;
import com.gl.cm.personas.mapper.PersonaMapper;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    @Transactional(readOnly = true)
    public List<PersonaDTO> getAll() {
        return this.personaRepository.findAll().stream().map(personaMapper::toPersonaDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonaDTO findById(UUID id) {
        Persona persona = this.personaRepository.findById(id).orElseThrow(() -> new PersonaNotFoundException("Persona not found"));
        return personaMapper.toPersonaDTO(persona);
    }

    @Transactional
    public PersonaDTO savePersona(PersonaDTO personaDTO) {
        Persona persona = personaMapper.toPersona(personaDTO);
        persona.getDirecciones().forEach(d -> d.setPersona(persona));
        return personaMapper.toPersonaDTO(personaRepository.saveAndFlush(persona));
    }

    @Transactional
    public PersonaDTO updatePersona(PersonaDTO personaDTO) {
        Persona persona = personaRepository.findById(personaDTO.getId())
                .orElseThrow(() -> new PersonaNotFoundException("Persona not found"));
        personaMapper.personaDTOtoPersona(personaDTO, persona);
        return personaMapper.toPersonaDTO(personaRepository.saveAndFlush(persona));
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
