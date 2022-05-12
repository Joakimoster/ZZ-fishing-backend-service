package com.example.ZZfishing.api.fish.repository.entity;

import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.api.fish.repository.enums.FishSpecies;

import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"catching","id"} )
public class Fish extends IdEntity {

    private int weight;
    private int length;

    @Enumerated(EnumType.STRING)
    private FishSpecies fishSpecies;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Catching catching;

    public Fish() {
    }

    public Fish(int weight, int length, FishSpecies fishSpecies, Catching catching) {
        this.weight = weight;
        this.length = length;
        this.fishSpecies = fishSpecies;
        this.catching = catching;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public FishSpecies getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(FishSpecies fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    public Catching getCatching() {
        return catching;
    }

    public void setCatching(Catching catching) {
        this.catching = catching;
    }

    @Override
    public String toString() {
        return "Fish{" +
                ", weight=" + weight +
                ", length=" + length +
                ", fishSpecies='" + fishSpecies + '\'' +
                '}';
    }
}
