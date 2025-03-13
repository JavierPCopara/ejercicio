package com.ec.ejercicio.controller;

import com.ec.ejercicio.dto.UsuarioDto;
import com.ec.ejercicio.enumeration.RestMensaje;
import com.ec.ejercicio.service.ClienteService;
import com.ec.ejercicio.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-url}/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody UsuarioDto usuarioDto) {
        try {
            clienteService.crearCliente(usuarioDto);
            return RestUtil.successfulCreate(RestMensaje.RESULTADO_OPERACION_EXITOSA.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable("id") Long id, @RequestBody UsuarioDto usuarioDto) {
        try {
            if (null != clienteService.actualizarCliente(id, usuarioDto))
                return RestUtil.successfulUpdate(RestMensaje.RESULTADO_OPERACION_EXITOSA.getValue());
            return RestUtil.notFoundData(RestMensaje.RESULTADO_OPERACION_NULL.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable("id") Long id) {
        try {
            clienteService.eliminarCliente(id);
            return RestUtil.successful(RestMensaje.RESULTADO_OPERACION_ELIMINAR_CLIENTE.getValue());
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }
}
