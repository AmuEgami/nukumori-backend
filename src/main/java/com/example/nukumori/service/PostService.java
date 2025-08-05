package com.example.nukumori.service;

import com.example.nukumori.model.Post;
import com.example.nukumori.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    // 投稿一覧取得
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        posts.sort((p1, p2) -> {
            try {
                LocalDateTime t1 = LocalDateTime.parse(p1.getCreatedAt(), FORMATTER);
                LocalDateTime t2 = LocalDateTime.parse(p2.getCreatedAt(), FORMATTER);
                return t2.compareTo(t1);
            } catch (Exception e) {
                return 0;
            }
        });

        return posts;
    }

    // 投稿作成
    public Post createPost(Post post) {
        post.setPostId(UUID.randomUUID().toString());

        // 保存形式をFORMATTERに揃える
        String formattedNow = LocalDateTime.now().format(FORMATTER);
        post.setCreatedAt(formattedNow);

        return postRepository.save(post);
    }
}
