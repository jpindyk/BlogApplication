package com.blogapplication.service;

import com.blogapplication.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    List<PostDto> findAllPosts();
    void createPost(PostDto postDto);
    PostDto findPostById (Long postId);
    void updatePost (PostDto postDto);
    void deletePost (Long postId);

    PostDto findPostByUrl (String postUrl);
    List<PostDto> searchPost (String query);
}
