package com.example.ZZfishing.api.history.repository.entity;

import com.example.ZZfishing.api.catching.repository.entity.Catching;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class History {

    @Id
    private Long Id;
    private Integer age;

    @OneToMany(mappedBy = "history")
    List<Catching> catchings;

    public History() {
    }

    public History(Long id, Integer age) {
        Id = id;
        this.age = age;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
                "Id=" + Id +
                ", age=" + age +
                '}';
    }
}
