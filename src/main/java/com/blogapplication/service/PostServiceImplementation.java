package com.blogapplication.service;

import com.blogapplication.mapper.PostMapper;
import com.blogapplication.model.Post;
import com.blogapplication.payload.PostDto;
import com.blogapplication.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        repository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = repository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        repository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = repository.findById(postId).get();
        repository.delete(post);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = repository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPost(String query) {
        return repository.searchPosts(query).stream()
                .map(p -> PostMapper.mapToPostDto(p))
                .collect(Collectors.toList());
    }
}
