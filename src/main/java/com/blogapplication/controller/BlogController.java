package com.blogapplication.controller;

import com.blogapplication.model.Post;
import com.blogapplication.payload.CommentDto;
import com.blogapplication.payload.PostDto;
import com.blogapplication.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
       List<PostDto> posts =  postService.findAllPosts();
       model.addAttribute("postsResponse", posts);
       return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable("postUrl") String postUrl,
                            Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        CommentDto commentDto = new CommentDto();
        model.addAttribute("post", post);
        model.addAttribute("comment", commentDto);
        return "blog/blog_post";
    }
    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> postResponse = postService.searchPost(query);
        model.addAttribute("postsResponse", postResponse);
        return "blog/view_posts";
    }

}
