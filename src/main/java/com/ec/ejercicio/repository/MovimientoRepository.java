package com.ec.ejercicio.repository;

import com.ec.ejercicio.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Movimiento findByNumeroCuentaAndEstado(String numeroCuenta, boolean estado);

    @Query(value = "SELECT * FROM movimiento m " +
            "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin AND m.numero_cuenta IN (SELECT c.numero_cuenta FROM cuenta c JOIN persona p ON c.persona_id = p.id WHERE p.identificacion = :identificacion)",
            nativeQuery = true)
    List<Movimiento> findByFechaBetweenAndIdentificacion1(@Param("fechaInicio") LocalDate fechaInicial,
                                                          @Param("fechaFin") LocalDate fechaFinal, @Param("identificacion") String identificacion);
}
