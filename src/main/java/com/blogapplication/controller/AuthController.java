package com.blogapplication.controller;

import com.blogapplication.model.User;
import com.blogapplication.payload.RegistrationDto;
import com.blogapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid @ModelAttribute("user") RegistrationDto registrationDto,
                           BindingResult result,
                           Model model) {

        User existingUser = userService.findByEmail(registrationDto.getEmail());
        if(existingUser != null){
            result.rejectValue("email", null, "There is already a user with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", registrationDto);
            return "register";
        }

        userService.saveUser(registrationDto);
        return "redirect:/register?success";
    }

}
