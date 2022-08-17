package com.blogapplication.service;

import com.blogapplication.payload.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
}
