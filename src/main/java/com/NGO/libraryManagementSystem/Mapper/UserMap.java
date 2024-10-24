package com.NGO.libraryManagementSystem.Mapper;

import com.NGO.libraryManagementSystem.DTO.SavedUserDto;
import com.NGO.libraryManagementSystem.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMap {
    public SavedUserDto UserToSavedUserDto(User user){

        SavedUserDto savedUserDto=new SavedUserDto();

        savedUserDto.setId(user.getId());

        savedUserDto.setEmail(user.getEmail());
        savedUserDto.setUsername(user.getUsername());
        savedUserDto.setPhoneNumber(user.getPhoneNumber());
        savedUserDto.setRole(user.getRole().name());
        return savedUserDto;
    }
}
