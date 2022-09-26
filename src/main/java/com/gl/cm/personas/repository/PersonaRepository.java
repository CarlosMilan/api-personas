package com.gl.cm.personas.repository;

import com.gl.cm.personas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonaRepository extends JpaRepository<Persona, UUID> {
}
