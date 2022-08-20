package com.blogapplication.repository;

import com.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);

    @Query ("Select p from Post p Where " +
            "p.title LIKE CONCAT('%', :query, '%') OR " +
            "p.description LIKE CONCAT('%', :query, '%') ")
    List<Post> searchPosts (String query);
}
