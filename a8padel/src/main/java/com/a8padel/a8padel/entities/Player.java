package com.a8padel.a8padel.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadores")
public class Player extends User{
    
    @Column
    private double level;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Training> classes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "grupo_clase_alumnos",
        joinColumns = @JoinColumn(name = "jugador_id"),
        inverseJoinColumns = @JoinColumn(name = "grupo_clase_id")
    )
    private List<GroupTraining> groups =  new ArrayList<>();

    public Player(){}
    
    public Player(String name, String lastName, String email, int phone, double level) {
        super(name, lastName, email, phone);
        this.level = level;
    }

    public Player(String name, String lastName, String username, String password, String email, int phone,
            double level) {
        super(name, lastName, username, password, email, phone);
        this.level = level;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public List<GroupTraining> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupTraining> grupos) {
        this.groups = grupos;
    }

    public List<Training> getClasses() {
        return classes;
    }

    public void setClasses(List<Training> classes) {
        this.classes = classes;
    }

    public void addGroup(GroupTraining group){
        this.groups.add(group);
        group.addPlayer(this);
    }
    
    public void removeGroup(GroupTraining group){
        this.groups.remove(group);
        group.removePlayer(this);
    }

    public void addClass(Training newClass){
        this.classes.add(newClass);
        if(!newClass.getPlayer().equals(this)){
            newClass.setPlayer(this);
        }
    }

    public void removeClass(Training removeClass){
        this.classes.remove(removeClass);
        removeClass.setPlayer(null);
    }

}
