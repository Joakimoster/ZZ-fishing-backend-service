package com.example.ZZfishing.api.history.repository.entity;

import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties( {"id"} )
public class History extends IdEntity {

    private Integer age;

    @OneToMany(mappedBy = "history")
    List<Catching> catchings;

    public History() {
    }

    public History(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Catching> getCatchings() {
        return catchings;
    }

    public void setCatchings(List<Catching> catchings) {
        this.catchings = catchings;
    }

    @Override
    public String toString() {
        return "History{" +
                ", age=" + age +
                '}';
    }
}
