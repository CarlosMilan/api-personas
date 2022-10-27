package com.gl.cm.personas.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class ProvinciaDTO {
    private UUID id;
    private String nombre;
}
