package com.ec.ejercicio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Entity
@Table(name = "persona")
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "identificacion", nullable = false, length = Integer.MAX_VALUE)
    private String identificacion;

    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;

    @Column(name = "telefono", length = Integer.MAX_VALUE)
    private String telefono;

    @Column(name = "genero", length = Integer.MAX_VALUE)
    private String genero;

}