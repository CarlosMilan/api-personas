package com.gl.cm.personas.repository;

import com.gl.cm.personas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {
}
