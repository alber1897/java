package com.projetsars.appbanco.model;


import java.time.LocalDate;
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
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String iban;

    @Column
    @NotBlank
    private Integer saldo;

    @Column
    @NotBlank
    private LocalDate fecha_apertura;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL)
    @Column
    private List <Transacciones> transaccionesOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL)
    @Column
    private List <Transacciones> transaccionesDestino = new ArrayList<>();

    public Cuenta(@NotBlank String iban, @NotBlank Integer saldo, @NotBlank LocalDate fecha_apertura,
            Cliente cliente) {
        this.iban = iban;
        this.saldo = saldo;
        this.fecha_apertura = fecha_apertura;
        this.cliente = cliente;
    }

    public Cuenta() {
    }

    public Cuenta(@NotBlank Integer saldo) {
        this.saldo = saldo;
    }


    public Long getId() {
        return id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transacciones> getTransaccionesOrigen() {
        return transaccionesOrigen;
    }

    public void setTransaccionesOrigen(List<Transacciones> transaccionesOrigen) {
        this.transaccionesOrigen = transaccionesOrigen;
    }

    public List<Transacciones> getTransaccionesDestino() {
        return transaccionesDestino;
    }

    public void setTransaccionesDestino(List<Transacciones> transaccionesDestino) {
        this.transaccionesDestino = transaccionesDestino;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(LocalDate fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }



    
}
