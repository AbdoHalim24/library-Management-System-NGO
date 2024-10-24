package com.NGO.libraryManagementSystem.Mapper;

import com.NGO.libraryManagementSystem.DTO.SavedAuthorDto;
import com.NGO.libraryManagementSystem.Entity.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorMap {
   public SavedAuthorDto AuthorToSavedAuthorDto(Author author){
       return new SavedAuthorDto(author.getId(),author.getName());
    }


}
