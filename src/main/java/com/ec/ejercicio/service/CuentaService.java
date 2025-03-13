package com.ec.ejercicio.service;

import com.ec.ejercicio.dto.CuentaDto;
import com.ec.ejercicio.exceptiom.CustomException;
import com.ec.ejercicio.model.Cuenta;
import com.ec.ejercicio.model.Movimiento;
import com.ec.ejercicio.model.Persona;
import com.ec.ejercicio.repository.CuentaRepository;
import com.ec.ejercicio.repository.MovimientoRepository;
import com.ec.ejercicio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    public void crearCuenta(CuentaDto cuentaDto) {
        Persona persona = personaRepository.findByIdentificacion(cuentaDto.getIdentificacion());
        Cuenta cuenta = new Cuenta();
        if(null != persona) {
            Movimiento movimiento = new Movimiento();
            cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
            cuenta.setEstado(cuentaDto.isEstado());
            cuenta.setPersona(persona);
            cuentaRepository.save(cuenta);
            movimiento.setFecha(LocalDate.now());
            movimiento.setTipoMovimiento(cuentaDto.getTipoMovimiento());
            movimiento.setValor(cuentaDto.getSaldoInicial());
            movimiento.setSaldo(cuentaDto.getSaldoInicial());
            movimiento.setNumeroCuenta(cuentaDto.getNumeroCuenta());
            movimiento.setEstado(true);
            movimientoRepository.save(movimiento);
        } else {
            throw new CustomException("Cliente no encontrado");
        }
    }

    @Transactional
    public ResponseEntity<?> actualizarCuenta(Long id, Cuenta cuenta) {
        if (cuentaRepository.existsById(id)) {
            cuenta.setId(id.intValue());
            return ResponseEntity.ok(cuentaRepository.save(cuenta));
        } else {
            return null;
        }
    }

    @Transactional
    public void eliminarCuenta(Long id) {
        if (cuentaRepository.findById(id).isPresent()) {
            cuentaRepository.delete(cuentaRepository.findById(id).get());
        }
    }
}
