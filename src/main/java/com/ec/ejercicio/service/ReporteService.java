package com.ec.ejercicio.service;

import com.ec.ejercicio.dto.ReporteDto;
import com.ec.ejercicio.exceptiom.CustomException;
import com.ec.ejercicio.model.Cuenta;
import com.ec.ejercicio.model.Movimiento;
import com.ec.ejercicio.repository.CuentaRepository;
import com.ec.ejercicio.repository.MovimientoRepository;
import com.ec.ejercicio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<ReporteDto> reportePorFechas(String inicio, String fin, String identificacion) {
        if (null == inicio || null == fin)
            throw new CustomException("Fechas incorrectas");
        LocalDate fechaInicial = LocalDate.parse(inicio);
        LocalDate fechaFinal = LocalDate.parse(fin);
        List<Movimiento> reporte = movimientoRepository.findByFechaBetweenAndIdentificacion1(fechaInicial, fechaFinal, identificacion);
        System.out.println(reporte);
        if (reporte.isEmpty())
            throw new CustomException("No existen movimientos");
        return setReporte(reporte);
    }

    private List<ReporteDto> setReporte(List<Movimiento> reporte) {
        List<ReporteDto> reporteDtos = new ArrayList<>();
        reporte.forEach(movimiento -> {
            ReporteDto reporteDto = new ReporteDto();
            Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getNumeroCuenta());
            reporteDto.setFecha(movimiento.getFecha());
            reporteDto.setCliente(cuenta.getPersona().getNombre());
            reporteDto.setNumeroCuenta(movimiento.getNumeroCuenta());
            reporteDto.setTipo(cuenta.getTipoCuenta());
            reporteDto.setSaldoInicial(cuenta.getSaldoInicial());
            reporteDto.setEstado(movimiento.isEstado());
            reporteDto.setMovimiento(movimiento.getValor());
            reporteDto.setSaldoDisponible(movimiento.getSaldo());
            reporteDtos.add(reporteDto);
        });
        return reporteDtos;
    }

}
