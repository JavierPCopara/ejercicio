package com.ec.ejercicio.dto;

import lombok.Data;

@Data
public class MovimientoDto {

    private String numeroCuenta;
    private String movimiento;
    private String tipo;
    private double valor;
}
