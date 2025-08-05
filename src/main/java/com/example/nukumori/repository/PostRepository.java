package com.example.nukumori.repository;

import com.example.nukumori.model.Post;
import java.util.List;

public interface PostRepository {
	// 投稿一覧取得
    List<Post> findAll();
    
    // 投稿作成
    Post save(Post post);
}
