package com.blogapplication.mapper;

import com.blogapplication.model.Post;
import com.blogapplication.payload.PostDto;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapToPostDto (Post post) {
        return  PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .description(post.getDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getCommentSet()
                        .stream()
                        .map(c->CommentMapper.mapToCommentDto(c))
                        .collect(Collectors.toSet()))
                .build();

    }

    public static Post mapToPost (PostDto postDto) {
        return  Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .description(postDto.getDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();

    }

}
