package com.gl.cm.personas.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class PersonaDTO {

    @NotNull
    private String dni;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z.\\-_]+@[0-9a-zA-Z]+(\\.[a-zA-Z]+)+$")
    private String email;
    @NotNull
    private LocalDate fechaNacimiento;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
