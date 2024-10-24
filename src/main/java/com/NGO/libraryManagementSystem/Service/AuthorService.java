package com.NGO.libraryManagementSystem.Service;

import com.NGO.libraryManagementSystem.DTO.AuthorDto;
import com.NGO.libraryManagementSystem.DTO.SavedAuthorDto;
import com.NGO.libraryManagementSystem.Entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author RetrieveAuthorByName(String author);

    List<SavedAuthorDto> RetrieveAllAuthors();

    Optional<Author> findAuthorById(Integer id);

    SavedAuthorDto createAuthor(AuthorDto authorDto);

    SavedAuthorDto updateAuthor(Integer id, AuthorDto authorDto);

    void deleteAuthor(Integer id);

    Author findAuthorByName(String author);

   Optional< Author> RetrieveAuthorById(Integer id);
}
