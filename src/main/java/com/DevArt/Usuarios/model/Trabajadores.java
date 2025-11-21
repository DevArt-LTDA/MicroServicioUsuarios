package com.DevArt.Usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabajadores")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Trabajadores {
    @Id
    @Column(name = "rut", length = 255, unique = true, nullable = false)
    private String rut;

    @Column(name = "primer_nombre", length = 255, unique = false, nullable = false)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 255, unique = false, nullable = true)
    private String segundoNombre;

    @Column(name = "primer_apellido", length = 255, unique = false, nullable = false)
    private String primApellido;

    @Column(name = "segundo_apellido", length = 255, unique = false, nullable = true)
    private String segApellido;

    @Column(name = "telefono", length = 255, unique = false, nullable = false)
    private String telefono;

    @Column(name = "correo", length = 255, unique = false, nullable = false)
    private String correo;

    @Column(name = "fecha_nacimiento", length = 255, unique = false, nullable = false)
    private String fechaNacimiento;

    @Column(name = "rol", length = 255, unique = false, nullable = false)
    private String rol;
}
