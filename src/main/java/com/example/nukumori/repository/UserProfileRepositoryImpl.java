package com.example.nukumori.repository;

import com.example.nukumori.model.UserProfile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.Optional;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private final DynamoDbTable<UserProfile> userProfileTable;

    public UserProfileRepositoryImpl(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.userProfileTable = enhancedClient.table("UserProfiles", TableSchema.fromBean(UserProfile.class));
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        userProfileTable.putItem(userProfile);
        return userProfile;
    }

    @Override
    public Optional<UserProfile> findById(String userId) {
        return Optional.ofNullable(
                userProfileTable.getItem(r -> r.key(k -> k.partitionValue(userId)))
        );
    }
}
