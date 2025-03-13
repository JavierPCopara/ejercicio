package com.ec.ejercicio.repository;

import com.ec.ejercicio.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Persona findByIdentificacion(String identificacion);
}
