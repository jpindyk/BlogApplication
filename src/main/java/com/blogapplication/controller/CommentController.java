package com.blogapplication.controller;

import com.blogapplication.payload.CommentDto;
import com.blogapplication.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/{postUrl}/comments")
    public String createComment (@PathVariable("postUrl") String postUrl,
                                 @ModelAttribute("comment") CommentDto commentDto,
                                 Model model){
       service.createComment(postUrl, commentDto);
       return "redirect:/post/" + postUrl;
    }
}
