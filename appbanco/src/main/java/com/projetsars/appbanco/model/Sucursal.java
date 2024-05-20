
package com.projetsars.appbanco.model;

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
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String nombre;

    @Column
    @NotBlank
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_banco")
    private Banco banco;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    @Column
    private List <Cliente> clientes = new ArrayList<>();

    

    public Sucursal(@NotBlank String nombre, @NotBlank String ubicacion, Banco banco, List<Cliente> clientes) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.banco = banco;
        this.clientes = clientes;
    }
    
    public Sucursal(@NotBlank String nombre, @NotBlank String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Sucursal(@NotBlank String nombre, @NotBlank String ubicacion, Banco banco) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.banco = banco;
    }

    public Long getId(){
        return this.id;
    }
    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }


    
}
