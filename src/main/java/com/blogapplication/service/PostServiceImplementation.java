package com.blogapplication.service;

import com.blogapplication.mapper.PostMapper;
import com.blogapplication.payload.PostDto;
import com.blogapplication.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImplementation implements PostService{

    private PostRepository repository;

    public PostServiceImplementation(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        return repository.findAll().stream()
                .map(s-> PostMapper.mapToPostDto(s))
                .collect(Collectors.toList());
    }
}
