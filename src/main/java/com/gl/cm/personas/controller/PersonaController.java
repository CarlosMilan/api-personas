package com.gl.cm.personas.controller;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(name = "/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> personas = personaService.getAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Persona> findById(@PathVariable(name = "id") UUID id) {
        Persona persona = personaService.findById(id);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Persona> save(@RequestBody PersonaDTO personaDTO) {
        Persona persona = personaService.savePersona(personaDTO);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Persona> update(@RequestBody PersonaDTO personaDTO, @PathVariable(name = "id") UUID uuid) {
        Persona persona = personaService.updatePersona(personaDTO, uuid);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id) {
        personaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
