package com.gl.cm.personas.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class PersonaDTO {

    private UUID id;
    @NotNull
    private String dni;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    @Email(regexp = ".+@.+\\..+")
    private String email;

    private String fechaNacimiento;

    private List<DireccionDTO> direcciones;

}
