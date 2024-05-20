package com.projetsars.appbanco.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @Column
    @NotBlank
    private String apellidos;

    @Column
    @NotBlank
    private Date fecha_nacimiento;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Column
    private List <Cuenta> cuentas = new ArrayList<>();


    public Cliente(@NotBlank String nombre, @NotBlank String apellidos, @NotBlank java.util.Date date,
            Sucursal sucursal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = (@NotBlank Date) date;
        this.sucursal = sucursal;
    }

    public Long getId() {
        return id;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    
}
