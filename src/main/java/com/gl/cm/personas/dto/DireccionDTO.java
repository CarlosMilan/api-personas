package com.gl.cm.personas.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DireccionDTO {
    private String calle;
    private Integer numeracion;
    private ProvinciaDTO provincia;
}
