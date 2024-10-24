package com.NGO.libraryManagementSystem.Controller;

import com.NGO.libraryManagementSystem.DTO.LoginDto;
import com.NGO.libraryManagementSystem.DTO.UserDto;
import com.NGO.libraryManagementSystem.Service.Imp.UserServiceImp;
import com.NGO.libraryManagementSystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    private  final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginDto loginDto){
        String response=userService.login(loginDto);
        if (response.equalsIgnoreCase("Invalid Email or Password")){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<?> UserRegister(@Validated @RequestBody UserDto userDto){
        if (!userDto.getRole().equalsIgnoreCase("user") &&! userDto.getRole().equalsIgnoreCase("admin")){
            return ResponseEntity.badRequest().body("Role must be either admin or user");
        }
        return ResponseEntity.ok(userService.Register(userDto));
    }
}
