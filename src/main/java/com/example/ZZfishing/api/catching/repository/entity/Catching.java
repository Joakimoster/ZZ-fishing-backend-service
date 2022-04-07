package com.example.ZZfishing.api.catching.repository.entity;

import com.example.ZZfishing.api.history.repository.entity.History;

import javax.persistence.*;

@Entity
@Table
public class Catching {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String fish;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    public Catching() {
    }

    public Catching(Long id, String fish) {
        Id = id;
        this.fish = fish;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
                "Id=" + Id +
                ", fish='" + fish + '\'' +
                '}';
    }
}
