package com.ec.ejercicio.repository;

import com.ec.ejercicio.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta findByNumeroCuenta(String numeroCuenta);
}
