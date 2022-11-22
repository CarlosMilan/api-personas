package com.gl.cm.personas.repository;

import com.gl.cm.personas.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DireccionesRepository extends JpaRepository<Direccion, UUID> {
    List<Direccion> findAllByPersonaId(UUID id);
}
