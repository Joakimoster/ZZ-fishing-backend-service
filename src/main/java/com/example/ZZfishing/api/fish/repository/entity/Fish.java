package com.example.ZZfishing.api.fish.repository.entity;

import javax.persistence.*;

@Entity
@Table
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
                "Id=" + Id +
                ", weight=" + weight +
                ", length=" + length +
                ", fishSpecies='" + fishSpecies + '\'' +
                '}';
    }
}
