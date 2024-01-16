package org.example.flyhigh.repository;

import org.example.flyhigh.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
