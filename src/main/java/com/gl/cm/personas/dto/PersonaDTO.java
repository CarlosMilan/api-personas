package com.gl.cm.personas.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    private String fechaNacimiento;

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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
