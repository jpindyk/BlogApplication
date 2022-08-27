package com.blogapplication.controller;

import com.blogapplication.payload.CommentDto;
import com.blogapplication.payload.PostDto;
import com.blogapplication.service.CommentService;
import com.blogapplication.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findPostsByUser();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    @GetMapping("admin/posts/newpost")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "admin/create_post";
    }

    @PostMapping("admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId,
                               Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult result,
                             Model model){


        if(result.hasErrors()){
            postDto.setId(postId);
            model.addAttribute("post", postDto);
            return "admin/edit_post";
        }
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,
                           Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> postDtos = postService.searchPost(query);
        model.addAttribute("posts", postDtos);
        return "admin/posts";

    }

    @GetMapping("/admin/posts/comments")
    public String postComments(Model model){
        List<CommentDto> comments = commentService.findCommentsByPost();
        model.addAttribute("comments", comments);
        return "admin/comments";
    }



    private static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url= title
                .replaceAll("\\s+", "-")
                .replaceAll("[^A-Za-z0-9]","");
        return url;
    }

}

