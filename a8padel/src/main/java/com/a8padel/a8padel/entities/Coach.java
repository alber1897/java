package com.a8padel.a8padel.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrenadores")
public class Coach extends User{
    
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<GroupTraining> coachingGroups = new ArrayList<>();

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Training> coachingClasses = new ArrayList<>();

    public Coach(){}
    
    public Coach(String name, String lastName, String username, String password, String email, int phone) {
        super(name, lastName, username, password, email, phone);
    }

    public Coach(String name, String lastName, String email, int phone) {
        super(name, lastName, email, phone);
    }

    public List<GroupTraining> getCoachingGroups() {
        return coachingGroups;
    }

    public void setCoachingGroups(List<GroupTraining> coachingGroups) {
        this.coachingGroups = coachingGroups;
    }

    public List<Training> getCoachingClasses() {
        return coachingClasses;
    }

    public void setCoachingClasses(List<Training> coachingClasses) {
        this.coachingClasses = coachingClasses;
    }

    public void addGroup(GroupTraining group){
        this.coachingGroups.add(group);
    }

    public void removeGroup(GroupTraining group){
        this.coachingGroups.remove(group);
        group.setCoach(null);
    }

    public void addClass(Training newClass){
        this.coachingClasses.add(newClass);
        if(!newClass.getCoach().equals(this)){
            newClass.setCoach(this);
        }
    }

    public void removeClass(Training removeClass){
        this.coachingClasses.remove(removeClass);
        removeClass.setCoach(null);
    }
}
