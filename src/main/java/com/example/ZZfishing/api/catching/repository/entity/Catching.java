package com.example.ZZfishing.api.catching.repository.entity;

import com.example.ZZfishing.api.history.repository.entity.History;
import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties( {"id"} )
public class Catching extends IdEntity {

    private String fish;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    public Catching() {
    }

    public Catching(String fish) {
        this.fish = fish;
    }

    public String getFish() {
        return fish;
    }

    public void setFish(String fish) {
        this.fish = fish;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Catching{" +
                ", fish='" + fish + '\'' +
                '}';
    }
}
