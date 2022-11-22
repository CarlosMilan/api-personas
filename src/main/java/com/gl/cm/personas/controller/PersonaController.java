package com.gl.cm.personas.controller;


import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.service.PersonaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/personas")
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping()
    public ResponseEntity<List<PersonaDTO>> findAll() {
        List<PersonaDTO> personas = personaService.getAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaDTO> findById(@PathVariable(name = "id") String id) {
        log.info("id: " + id);
        PersonaDTO persona = personaService.findById(UUID.fromString(id));
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaDTO> save(@Valid @RequestBody PersonaDTO personaDTO) {
        log.info("Save: " + personaDTO);
        PersonaDTO persona = personaService.savePersona(personaDTO);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaDTO> update(@Valid @RequestBody PersonaDTO personaDTO) {
        log.info("Update: " + personaDTO);
        PersonaDTO persona = personaService.updatePersona(personaDTO);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        log.info("Delete:  " + id);
        personaService.delete(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
