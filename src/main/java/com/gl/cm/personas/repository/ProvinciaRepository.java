package com.gl.cm.personas.repository;

import com.gl.cm.personas.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
    Optional<Provincia> findByNombreIgnoreCase(String nombre);
}
