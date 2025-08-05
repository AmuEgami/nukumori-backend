package com.example.nukumori.repository;

import com.example.nukumori.model.UserProfile;

import java.util.Optional;

public interface UserProfileRepository {
    UserProfile save(UserProfile userProfile);
    Optional<UserProfile> findById(String userId);
}
