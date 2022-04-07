package com.example.ZZfishing.api.fishinglure.repository.entity;

import javax.persistence.*;

@Entity
@Table
public class FishingLure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String type;
    private Integer length;
    private Integer weight;
    private String label;

    public FishingLure() {
    }

    public FishingLure(Long id, String type, Integer length, Integer weight, String label) {
        Id = id;
        this.type = type;
        this.length = length;
        this.weight = weight;
        this.label = label;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "FishingLure{" +
                "Id=" + Id +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                ", label='" + label + '\'' +
                '}';
    }
}
