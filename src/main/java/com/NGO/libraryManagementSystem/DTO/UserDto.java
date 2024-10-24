package com.NGO.libraryManagementSystem.DTO;

import com.NGO.libraryManagementSystem.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @NotBlank
    @NotNull
    @Size(max = 30,min = 3,message = "enter a valid number of char in name ")
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 6,message = "enter a valid number of char in password")
    private String password;
    @NotBlank
    @NotNull
    private String phoneNumber;
    @NotNull(message = "role must be admin or user not null")
    @NotBlank(message = "role must be admin or user not blank")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
