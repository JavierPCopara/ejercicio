package com.ec.ejercicio.dto;

import lombok.Data;


@Data
public class CuentaDto {

    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private boolean estado;
    private String tipoMovimiento;
    private String identificacion;
}
