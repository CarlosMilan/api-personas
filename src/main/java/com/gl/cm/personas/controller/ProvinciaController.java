package com.gl.cm.personas.controller;

import com.gl.cm.personas.dto.ProvinciaDTO;
import com.gl.cm.personas.service.ProvinciaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincias")
@AllArgsConstructor
@Slf4j
public class ProvinciaController {

    private final ProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<List<ProvinciaDTO>> getProvincias() {
        log.info("Get provincias");
        return new ResponseEntity<>(provinciaService.getAll(), HttpStatus.OK);
    }

}
