package com.gl.cm.personas.dto;

import lombok.Data;

@Data
public class DireccionDTO {
    private String calle;
    private Integer numeracion;
    private ProvinciaDTO provincia;
}
