package com.ec.ejercicio.util;

import com.ec.ejercicio.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class RestUtil {

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<Object> handleRestExceptions(Exception ex) {
        ResponseVo apiError = new ResponseVo();
        apiError.setMensaje(ex.getMessage());
        apiError.setResultado(Boolean.FALSE);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<Object> handleExceptions(String ex) {
        ResponseVo error = new ResponseVo();
        error.setMensaje(ex);
        error.setResultado(Boolean.FALSE);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> successful(String mensaje) {
        ResponseVo api = new ResponseVo();
        api.setMensaje(mensaje);
        api.setResultado(Boolean.TRUE);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    public static ResponseEntity<Object> successfulCreate(String mensaje) {
        ResponseVo api = new ResponseVo();
        api.setMensaje(mensaje);
        api.setResultado(Boolean.TRUE);
        return new ResponseEntity<>(api, HttpStatus.CREATED);
    }

    public static ResponseEntity<Object> successfulUpdate(String mensaje) {
        ResponseVo api = new ResponseVo();
        api.setMensaje(mensaje);
        api.setResultado(Boolean.TRUE);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    public static ResponseEntity<Object> notFoundData(String mensaje) {
        ResponseVo api = new ResponseVo();
        api.setMensaje(mensaje);
        api.setResultado(Boolean.FALSE);
        return new ResponseEntity<>(api, HttpStatus.BAD_GATEWAY);
    }
}
