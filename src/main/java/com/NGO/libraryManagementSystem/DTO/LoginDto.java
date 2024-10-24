package com.NGO.libraryManagementSystem.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public class LoginDto {
    @NotBlank(message = "email must be not blank")
    @NotNull(message = "email must be not null")
    private String email;
    @NotBlank(message = "password must be not blank")
    @NotNull(message = "password must be not null")
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
