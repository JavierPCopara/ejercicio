package com.ec.ejercicio.controller;

import com.ec.ejercicio.dto.UsuarioDto;
import com.ec.ejercicio.service.ReporteService;
import com.ec.ejercicio.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-url}/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reporteFechas(@RequestParam("fechaInicio") String fechaInicio,
                                           @RequestParam("fechaFin") String fechaFin, @RequestBody UsuarioDto usuario) {
        try {
            return ResponseEntity.ok(reporteService.reportePorFechas(fechaInicio, fechaFin, usuario.getIdentificacion()));
        } catch (Exception e) {
            return RestUtil.handleRestExceptions(e);
        }
    }
}
