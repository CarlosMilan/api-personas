package com.gl.cm.personas.controller;


import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping()
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> personas = personaService.getAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> findById(@PathVariable(name = "id") String id) {
        Persona persona = personaService.findById(UUID.fromString(id));
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> save(@Valid @RequestBody PersonaDTO personaDTO) {
        Persona persona = personaService.savePersona(personaDTO);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Persona> update(@Valid @RequestBody PersonaDTO personaDTO, @PathVariable(name = "id") String uuid) {
        Persona persona = personaService.updatePersona(personaDTO, UUID.fromString(uuid));
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        personaService.delete(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
