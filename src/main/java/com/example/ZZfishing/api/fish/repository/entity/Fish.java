package com.example.ZZfishing.api.fish.repository.entity;

import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties( {"id"} )
public class Fish extends IdEntity {

    private int weight;
    private int length;
    private String fishSpecies;

    public Fish() {
    }

    public Fish(int weight, int length, String fishSpecies) {
        this.weight = weight;
        this.length = length;
        this.fishSpecies = fishSpecies;
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

    public String getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(String fishSpecies) {
        this.fishSpecies = fishSpecies;
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
