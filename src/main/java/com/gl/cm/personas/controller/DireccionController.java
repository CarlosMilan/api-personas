package com.gl.cm.personas.controller;

import com.gl.cm.personas.dto.DireccionDTO;
import com.gl.cm.personas.service.DireccionesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/direcciones")
@AllArgsConstructor
public class DireccionController {

    //CRUD Direcciones y obtener por id_persona
    private final DireccionesService direccionesService;

    @GetMapping(value = "/{idPersona}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DireccionDTO>> getDireccionesByIdPersona(@PathVariable("idPersona") String idPersona) {
        log.info("Obtener direcciones por id: " + idPersona);
        return new ResponseEntity<>(direccionesService.getDirecciones(idPersona), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DireccionDTO> save(@Valid @RequestBody DireccionDTO direccionDTO) {
        log.info("Guardando direccion: " + direccionDTO);
        return new ResponseEntity<>(direccionesService.save(direccionDTO), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DireccionDTO> update(DireccionDTO direccionDTO) {
        log.info("Update: " + direccionDTO);
        return new ResponseEntity<>(direccionesService.update(direccionDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete: " + id);
        direccionesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
