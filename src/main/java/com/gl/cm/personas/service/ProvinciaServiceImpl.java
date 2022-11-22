package com.gl.cm.personas.service;

import com.gl.cm.personas.dto.ProvinciaDTO;
import com.gl.cm.personas.repository.ProvinciaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProvinciaServiceImpl implements ProvinciaService{

    private final ProvinciaRepository provinciaRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProvinciaDTO> getAll() {
        return provinciaRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, ProvinciaDTO.class))
                .collect(Collectors.toList());
    }
}
