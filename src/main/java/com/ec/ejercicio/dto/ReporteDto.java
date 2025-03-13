package com.ec.ejercicio.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReporteDto {

    private LocalDate fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private boolean estado;
    private double movimiento;
    private double saldoDisponible;
}
