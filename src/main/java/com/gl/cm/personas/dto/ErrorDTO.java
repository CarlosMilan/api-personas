package com.gl.cm.personas.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDTO {
    private Integer codigo;
    private String mensaje;
    private LocalDateTime fecha;

    public ErrorDTO(Integer codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }
}
