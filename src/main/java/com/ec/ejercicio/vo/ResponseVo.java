package com.ec.ejercicio.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo implements Serializable{

    private String mensaje;
    private boolean resultado;


}
