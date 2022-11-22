package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.ResourceNotFoundException;
import com.gl.cm.personas.mapper.PersonaMapper;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.model.Provincia;
import com.gl.cm.personas.repository.PersonaRepository;
import com.gl.cm.personas.repository.ProvinciaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;
    private final ProvinciaRepository provinciaRepository;
    private final PersonaMapper personaMapper;

    @Transactional(readOnly = true)
    public List<PersonaDTO> getAll() {
        return this.personaRepository.findAll().stream().map(personaMapper::toPersonaDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonaDTO findById(UUID id) {
        log.info("Buscar persona por id: {}", id);
        Persona persona = this.personaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Persona not found"));
        log.info("Persona encontrada: {}", persona);
        PersonaDTO personaDTO = personaMapper.toPersonaDTO(persona);
        log.trace("personaDTO: {}", personaDTO);
        return personaDTO;
    }

    @Transactional
    public PersonaDTO savePersona(PersonaDTO personaDTO) {
        log.info("Creando persona: {}", personaDTO);
        Persona persona = personaMapper.toPersona(personaDTO);
        log.trace("persona: {}", persona);
        Persona savedPersona = personaRepository.save(persona);
        log.info("Persona guardada: {}", savedPersona);
        PersonaDTO savedPersonaDTO = personaMapper.toPersonaDTO(savedPersona);
        log.trace("personaDTO: {}", savedPersonaDTO);
        return savedPersonaDTO;
    }

    @Transactional
    public PersonaDTO updatePersona(PersonaDTO personaDTO) {
        log.info("Actualizando persona: {}", personaDTO);
        Persona persona = personaRepository.findById(personaDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Persona not found"));
        log.trace("persona: {}", persona);
        personaMapper.personaDTOtoPersona(personaDTO, persona);
        return personaMapper.toPersonaDTO(personaRepository.saveAndFlush(persona));
    }

    @Transactional
    public void delete(UUID id) {

        if (personaRepository.existsById(id)) {
            log.info("Eliminando persona con id: {}", id);
            personaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Persona Not Found");
        }
    }
}
