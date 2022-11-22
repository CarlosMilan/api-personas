package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.DireccionDTO;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.exception.ResourceNotFoundException;
import com.gl.cm.personas.model.Direccion;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.model.Provincia;
import com.gl.cm.personas.repository.DireccionesRepository;
import com.gl.cm.personas.repository.PersonaRepository;
import com.gl.cm.personas.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DireccionesServiceImpl implements DireccionesService {

    private final DireccionesRepository repository;

    private final ProvinciaRepository provinciaRepository;
    private final PersonaRepository personaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public DireccionDTO save(DireccionDTO direccionDTO) {
        Direccion direccion = modelMapper.map(direccionDTO, Direccion.class);

        UUID idPersona = direccionDTO.getPersona().getId();
        UUID idProvincia = direccionDTO.getProvincia().getId();

        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not found"));
        Provincia provincia = provinciaRepository.findById(idProvincia)
                .orElseThrow(() -> new ResourceNotFoundException("Provincia not found"));

        direccion.setPersona(persona);
        direccion.setProvincia(provincia);

        return modelMapper.map(repository.save(direccion), DireccionDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DireccionDTO> getDirecciones(PersonaDTO personaDTO) {
        Persona persona = modelMapper.map(personaDTO, Persona.class);
        return repository.findAllByPersonaId(persona.getId())
                .stream().map(d -> modelMapper.map(d, DireccionDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DireccionDTO> getDirecciones(String idPersona) {
        return repository.findAllByPersonaId(UUID.fromString(idPersona))
                .stream().map(d -> modelMapper.map(d, DireccionDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public DireccionDTO update(DireccionDTO direccionDTO) {
        Direccion direccion = repository.findById(direccionDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Direccion not found"));
        modelMapper.map(direccionDTO, direccion);
        return modelMapper.map(repository.save(direccion), DireccionDTO.class);
    }

    @Transactional
    public void delete(String idDireccion) {
        if (repository.existsById(UUID.fromString(idDireccion))) {
            repository.deleteById(UUID.fromString(idDireccion));
        } else {
            throw new ResourceNotFoundException("Direccion not found");
        }
    }

}
