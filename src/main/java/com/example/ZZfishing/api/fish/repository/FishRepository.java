package com.example.ZZfishing.api.fish.repository;

import com.example.ZZfishing.api.fish.repository.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {
}
