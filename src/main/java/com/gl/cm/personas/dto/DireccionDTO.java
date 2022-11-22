package com.gl.cm.personas.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DireccionDTO {
    private UUID id;
    private String calle;
    private Integer numeracion;
    private PersonaDTO persona;
    private ProvinciaDTO provincia;
}
