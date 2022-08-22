package com.blogapplication.service;

import com.blogapplication.payload.CommentDto;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
}
