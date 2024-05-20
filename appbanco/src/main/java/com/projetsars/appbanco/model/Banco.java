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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String nombre;

    @Column
    @NotBlank
    private String pais;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL)
    @Column
    private List <Sucursal> sucursales = new ArrayList<>();

    public Banco(@NotBlank String nombre, @NotBlank String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public Long getId(){
        return this.id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }


    
}
