package com.gl.cm.personas.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
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
