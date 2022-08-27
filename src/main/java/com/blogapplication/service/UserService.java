package com.blogapplication.service;

import com.blogapplication.model.User;
import com.blogapplication.payload.RegistrationDto;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
