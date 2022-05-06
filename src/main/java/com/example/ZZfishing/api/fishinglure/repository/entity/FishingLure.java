package com.example.ZZfishing.api.fishinglure.repository.entity;

import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties( {"id"} )
public class FishingLure extends IdEntity {

    private String type;
    private int length;
    private int weight;
    private String label;

    public FishingLure() {
    }

    public FishingLure(String type, int length, int weight, String label) {
        this.type = type;
        this.length = length;
        this.weight = weight;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
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
                ", type='" + type + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                ", label='" + label + '\'' +
                '}';
    }
}
