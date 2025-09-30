package com.blog.blog_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "Email can not being blank!")
    @Email(message = "Enter a valid email!")
    private String email;

    @NotBlank(message = "Password can not being blank!")
    @Size(min = 8, max = 16, message = "Password's length must be minimum 8 characters, maximum 16 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Passwords include only characters and numbers." )
    private String password;

    @NotBlank(message = "Full name can not being blank!")
    private String fullName;
}