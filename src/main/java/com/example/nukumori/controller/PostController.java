package com.example.nukumori.controller;


import org.springframework.web.bind.annotation.*;

import com.example.nukumori.model.Post;
import com.example.nukumori.service.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 投稿一覧取得API（GET /posts）
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    // 投稿作成API
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post saved = postService.createPost(post);
        System.out.println("【投稿内容の確認】" + post.getContent());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
