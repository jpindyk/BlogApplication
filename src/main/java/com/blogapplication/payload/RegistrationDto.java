package com.blogapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private Long id;
    @NotBlank(message = "First name should not be blank or empty")
    private String firstName;
    @NotBlank(message = "Last name should not be blank or empty")
    private String lastName;
    @NotBlank(message = "Email should not be blank or empty")
    @Email(message = "You need to pass proper email")
    private String email;
    @NotBlank(message = "Password should not be blank or empty")
    private String password;
}
