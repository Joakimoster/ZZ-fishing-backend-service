package com.example.ZZfishing.api.profile.repository;

import com.example.ZZfishing.api.profile.repository.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findProfileByEmail(String email);
}
