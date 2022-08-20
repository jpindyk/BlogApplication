package com.blogapplication.mapper;

import com.blogapplication.model.Comment;
import com.blogapplication.model.Post;
import com.blogapplication.payload.CommentDto;
import com.blogapplication.payload.PostDto;

public class CommentMapper {

    public static CommentDto mapToCommentDto (Comment comment) {
        return  CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();

    }

    public static Comment mapToComment (CommentDto commentDto) {
        return  Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();

    }
}
