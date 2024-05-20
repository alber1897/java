package com.projetsars.appbanco.model;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacciones")
public class Transacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "cuentaOrigen")
    private Cuenta cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuentaDestino")
    private Cuenta cuentaDestino;

    @Column
    @NotBlank
    private int cantidad;

    public Transacciones(@NotBlank Date fecha, @NotBlank Cuenta cuentaOrigen, @NotBlank Cuenta cuentaDestino,
            @NotBlank int cantidad) {
        this.fecha = fecha;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
