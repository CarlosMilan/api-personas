package com.gl.cm.personas.dto;

import java.time.LocalDateTime;

public class ErrorDTO {
    private Integer codigo;
    private String mensaje;
    private LocalDateTime fecha;

    public ErrorDTO(Integer codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
