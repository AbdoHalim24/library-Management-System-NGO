package com.NGO.libraryManagementSystem.Controller;

import com.NGO.libraryManagementSystem.DTO.AuthorDto;
import com.NGO.libraryManagementSystem.DTO.SavedAuthorDto;
import com.NGO.libraryManagementSystem.DTO.SavedBookDto;
import com.NGO.libraryManagementSystem.Entity.Author;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Mapper.AuthorMap;
import com.NGO.libraryManagementSystem.Service.AuthorService;
import com.NGO.libraryManagementSystem.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthoritiesAuthorizationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final AuthorMap authorMapper;

    public AuthorController(AuthorService authorService, BookService bookService, AuthorMap authorMapper) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.authorMapper = authorMapper;
    }
    @GetMapping
    public ResponseEntity<List<SavedAuthorDto>> RetrieveAllAuthors(){
        return ResponseEntity.ok(authorService.RetrieveAllAuthors());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> RetrieveAuthor(@PathVariable Integer id){
        Optional<Author> author=authorService.findAuthorById(id);
        if (author.isEmpty())
        {
            return ResponseEntity.badRequest().body("Author with this id not exist");
        }
        return ResponseEntity.ok(authorMapper.AuthorToSavedAuthorDto(author.get()));
    }
    @PostMapping
    public ResponseEntity<SavedAuthorDto> createAuthor(@Validated @RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.createAuthor(authorDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id,@Validated@ RequestBody AuthorDto authorDto){
        Optional<Author> author=authorService.findAuthorById(id);
        if (author.isEmpty()){
            return ResponseEntity.badRequest().body("Author with this id not exist");
        }
        return ResponseEntity.ok(authorService.updateAuthor(id,authorDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id){
        Optional<Author> author=authorService.findAuthorById(id);
        if (author.isEmpty()){
            return ResponseEntity.badRequest().body("Author with this id not exist");
        }
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<List<SavedBookDto>>retrieveAuthorBook(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.retrieveAuthorBooks(id));
    }
}
