package com.gl.cm.personas.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private LocalDateTime creacion;
    private Boolean activo;

    @PrePersist
    protected void prePersist() {
        if (this.activo == null)
            this.activo = true;
        if (this.creacion == null)
            this.creacion = LocalDateTime.now();
    }
}
