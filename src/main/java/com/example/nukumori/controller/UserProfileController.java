package com.example.nukumori.controller;

import com.example.nukumori.model.UserProfile;
import com.example.nukumori.service.UserProfileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(UserProfileService profileService) {
    	System.out.println("✅✅✅ UserProfileController initialized ✅✅✅");
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile) {
    	System.out.println("保存前のUserProfile:");
    	System.out.println("userId: " + profile.getUserId());
    	System.out.println("userName: " + profile.getUserName());
    	System.out.println("bio: " + profile.getBio());
    	System.out.println("mbti: " + profile.getMbti());
    	UserProfile saved = profileService.saveProfile(profile);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable String userId) {
    	System.out.println("getProfile: userId = " + userId);
        return profileService.getProfile(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
