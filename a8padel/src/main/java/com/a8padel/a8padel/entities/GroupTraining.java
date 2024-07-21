package com.a8padel.a8padel.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupos_clases")
public class GroupTraining {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "groups")
    private List <Player> players = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Coach coach;

    @Column
    private int weekday;

    @Column
    private Time startHour;

    @Column
    private Time endHour;

    public GroupTraining() {
    }

    public GroupTraining(String name, List<Player> players, Coach coach, int weekday, Time startHour, Time endHour) {
        this.name = name;
        this.players = players;
        this.coach = coach;
        this.weekday = weekday;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
        if(!coach.getCoachingGroups().contains(this)){
            coach.addGroup(this);
        }
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public Time getStartHour() {
        return startHour;
    }

    public void setStartHour(Time startHour) {
        this.startHour = startHour;
    }

    public Time getEndHour() {
        return endHour;
    }

    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }

    public void addPlayer(Player player){
        this.players.add(player);
        player.addGroup(this);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
        player.removeGroup(this);
    }
}
