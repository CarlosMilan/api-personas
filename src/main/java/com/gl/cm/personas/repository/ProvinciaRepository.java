package com.gl.cm.personas.repository;

import com.gl.cm.personas.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
    Optional<Provincia> findByNombreIgnoreCase(String nombre);
}
