package com.example.nukumori.repository;

import com.example.nukumori.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Post";

    @Autowired
    public PostRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }
    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        ScanRequest scanRequest = ScanRequest.builder().tableName(tableName).build();
        ScanResponse response = dynamoDbClient.scan(scanRequest);
        for (Map<String, AttributeValue> item : response.items()) {
            Post post = new Post();
            post.setPostId(item.get("postId").s());
            post.setUserName(item.get("userName").s());
            post.setContent(item.get("content").s());
            post.setCreatedAt(item.get("createdAt").s());
            posts.add(post);
        }
        return posts;
    }
    
    // 投稿作成
    @Override
    public Post save(Post post) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("postId", AttributeValue.builder().s(post.getPostId()).build());
        item.put("userName", AttributeValue.builder().s(post.getUserName()).build());
        item.put("content", AttributeValue.builder().s(post.getContent()).build());
        item.put("createdAt", AttributeValue.builder().s(post.getCreatedAt()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);

        return post; 
    }
}
