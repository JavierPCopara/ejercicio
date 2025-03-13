package com.ec.ejercicio.service;

import com.ec.ejercicio.dto.MovimientoDto;
import com.ec.ejercicio.exceptiom.CustomException;
import com.ec.ejercicio.model.Movimiento;
import com.ec.ejercicio.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional
    public Movimiento crearMovimiento(MovimientoDto movimientoDto) {
        Movimiento actual = movimientoRepository.findByNumeroCuentaAndEstado(movimientoDto.getNumeroCuenta(), true);
        validarMovimiento(movimientoDto);
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDate.now());
        movimiento.setEstado(true);
        movimiento.setNumeroCuenta(movimientoDto.getNumeroCuenta());
        if (actual != null) {
            if (movimientoDto.getValor() < 0)
                validarSaldo(actual, movimientoDto.getValor());
            movimiento.setSaldo(actual.getSaldo() + movimientoDto.getValor());
            movimiento.setTipoMovimiento(movimientoDto.getTipo());
            movimiento.setValor(movimientoDto.getValor());
            actual.setEstado(false);
            movimientoRepository.save(actual);
        } else {
            movimiento.setSaldo(movimiento.getValor());
        }
        return movimientoRepository.save(movimiento);
    }

    @Transactional
    public ResponseEntity<?> actualizarMovimiento(Long id, Movimiento movimiento) {
        if (movimientoRepository.existsById(id)) {
            movimiento.setId(id.intValue());
            return ResponseEntity.ok(movimientoRepository.save(movimiento));
        } else {
            return null;
        }
    }

    @Transactional
    public void eliminarMovimiento(Long id) {
        if (movimientoRepository.findById(id).isPresent()) {
            movimientoRepository.delete(movimientoRepository.findById(id).get());
        }
    }

    private void validarSaldo(Movimiento actual, Double valor) {
        if (actual.getSaldo() <= 0) {
            throw new CustomException("Saldo no disponible");
        }
        if (actual.getSaldo() < Math.abs(valor)) {
            throw new CustomException("Fondos insuficiente");
        }
    }

    private void validarMovimiento(MovimientoDto movimientoDto) {
        Pattern patron = Pattern.compile("\\d+");
        Matcher matcher = patron.matcher(movimientoDto.getMovimiento());
        if (matcher.find()) {
            movimientoDto.setValor(Double.parseDouble(matcher.group()));
        } else {
            throw new CustomException("Cantidad invalida");
        }
        if (movimientoDto.getMovimiento().contains("Retiro")) {
            movimientoDto.setValor(-Double.parseDouble(matcher.group()));
            movimientoDto.setTipo("Retiro");
        } else {
            movimientoDto.setTipo("Deposito");
        }
    }
}
