package com.example.ZZfishing.api.catching.repository.entity;

import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.api.history.repository.entity.History;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Catching {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Date catchDate;

    private String weather;

    @OneToOne(mappedBy = "catching", cascade = CascadeType.ALL)
    private Fish fish;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    public Catching() {
    }

    public Catching(Long id, Fish fish) {
        Id = id;
        this.fish = fish;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Date getCatchDate() {
        return catchDate;
    }

    public void setCatchDate(Date catchDate) {
        this.catchDate = catchDate;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
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
