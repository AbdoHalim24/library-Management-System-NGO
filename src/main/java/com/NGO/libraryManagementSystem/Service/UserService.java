package com.NGO.libraryManagementSystem.Service;


import com.NGO.libraryManagementSystem.DTO.LoginDto;
import com.NGO.libraryManagementSystem.DTO.SavedUserDto;
import com.NGO.libraryManagementSystem.DTO.UserDto;

public interface UserService {
    String login(LoginDto loginDto);

    SavedUserDto Register(UserDto userDto);
}
