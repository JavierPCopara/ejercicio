package com.ec.ejercicio.controller;

import com.ec.ejercicio.dto.CuentaDto;
import com.ec.ejercicio.enumeration.RestMensaje;
import com.ec.ejercicio.model.Cuenta;
import com.ec.ejercicio.service.CuentaService;
import com.ec.ejercicio.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-url}/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDto cuenta) {
        try {
            cuentaService.crearCuenta(cuenta);
            return RestUtil.successfulCreate(RestMensaje.RESULTADO_OPERACION_EXITOSA.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuenta) {
        try {
            if (null != cuentaService.actualizarCuenta(id, cuenta))
                return RestUtil.successfulUpdate(RestMensaje.RESULTADO_OPERACION_EXITOSA.getValue());
            return RestUtil.notFoundData(RestMensaje.RESULTADO_OPERACION_NULL.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable("id") Long id) {
        try {
            cuentaService.eliminarCuenta(id);
            return RestUtil.successful(RestMensaje.RESULTADO_OPERACION_ELIMINAR_CUENTA.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }
}
