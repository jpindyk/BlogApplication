package com.blogapplication.service;

import com.blogapplication.mapper.CommentMapper;
import com.blogapplication.model.Comment;
import com.blogapplication.model.Post;
import com.blogapplication.payload.CommentDto;
import com.blogapplication.repository.CommentRepository;
import com.blogapplication.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService{

    private CommentRepository commentRepository;
    private PostRepository postRepository;


    public CommentServiceImplementation(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }
}
