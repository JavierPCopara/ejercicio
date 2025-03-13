package com.ec.ejercicio.controller;

import com.ec.ejercicio.dto.MovimientoDto;
import com.ec.ejercicio.enumeration.RestMensaje;
import com.ec.ejercicio.model.Movimiento;
import com.ec.ejercicio.service.MovimientoService;
import com.ec.ejercicio.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-url}/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoDto movimientoDto) {
        try {
            movimientoService.crearMovimiento(movimientoDto);
            return RestUtil.successfulCreate(RestMensaje.RESULTADO_OPERACION_EXITOSA.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMovimiento(@PathVariable("id") Long id, @RequestBody Movimiento movimiento) {
        try {
            if (null != movimientoService.actualizarMovimiento(id, movimiento))
                return RestUtil.successfulUpdate(RestMensaje.RESULTADO_OPERACION_EXITOSA.getValue());
            return RestUtil.notFoundData(RestMensaje.RESULTADO_OPERACION_NULL.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMovimiento(@PathVariable("id") Long id) {
        try {
            movimientoService.eliminarMovimiento(id);
            return RestUtil.successful(RestMensaje.RESULTADO_OPERACION_ELIMINAR_MOVIMIENTO.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }
}
