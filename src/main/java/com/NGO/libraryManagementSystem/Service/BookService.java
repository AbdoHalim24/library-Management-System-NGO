package com.NGO.libraryManagementSystem.Service;

import com.NGO.libraryManagementSystem.DTO.BookDto;
import com.NGO.libraryManagementSystem.DTO.SavedBookDto;
import com.NGO.libraryManagementSystem.DTO.SearchDto;
import com.NGO.libraryManagementSystem.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<SavedBookDto> retrieveBooks();

    Optional<Book> retrieveBook(Integer id);

    SavedBookDto createBook(BookDto book);

    SavedBookDto updateBook(Integer id, BookDto bookDto);

    void deleteBook(Integer id);

    List<SavedBookDto> retrieveSearchBooks(SearchDto searchDto);

    List<SavedBookDto> retrieveAuthorBooks(Integer id);
}
