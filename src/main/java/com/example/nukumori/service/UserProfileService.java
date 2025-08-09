package com.example.nukumori.service;

import com.example.nukumori.model.UserProfile;
import com.example.nukumori.repository.UserProfileRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }
    
    // プロフィール登録 
    public UserProfile saveProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }
    
    // プロフィール取得
    public Optional<UserProfile> getProfile(String userId) {
    	System.out.println("Service: getProfile called with userId = " + userId);
        Optional<UserProfile> profile = userProfileRepository.findById(userId);
        System.out.println("Service: Retrieved profile = " + profile);
        return profile;
    }
}
