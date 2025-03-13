package com.ec.ejercicio.enumeration;

import lombok.Getter;

@Getter
public enum RestMensaje {
    RESULTADO_OPERACION_EXITOSA("Operación exitosa"),
    RESULTADO_OPERACION_NULL("Elemento no encontrado"),
    RESULTADO_OPERACION_ELIMINAR_CUENTA("Cuenta eliminada"),
    RESULTADO_OPERACION_ELIMINAR_CLIENTE("Cliente eliminado"),
    RESULTADO_OPERACION_ELIMINAR_MOVIMIENTO("Movimiento eliminado");

    private String value;

    RestMensaje(String value) {
        this.value = value;
    }
}
