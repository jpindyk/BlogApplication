package com.blogapplication.service;

import com.blogapplication.mapper.PostMapper;
import com.blogapplication.model.Post;
import com.blogapplication.model.User;
import com.blogapplication.payload.PostDto;
import com.blogapplication.repository.PostRepository;
import com.blogapplication.repository.UserRepository;
import com.blogapplication.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    private PostRepository repository;
    private UserRepository userRepository;

    public PostServiceImplementation(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {

        return repository.findAll().stream()
                .map(s -> PostMapper.mapToPostDto(s))
                .collect(Collectors.toList());
    }
    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();

        List<Post> posts = repository.findPostsByUser(userId);
        return posts.stream().map(
                p->PostMapper.mapToPostDto(p)
        ).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);

        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        repository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = repository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
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
