package com.NGO.libraryManagementSystem.Controller;

import com.NGO.libraryManagementSystem.DTO.BookDto;
import com.NGO.libraryManagementSystem.DTO.SavedBookDto;
import com.NGO.libraryManagementSystem.DTO.SearchDto;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Mapper.BookMap;
import com.NGO.libraryManagementSystem.Service.AuthorService;
import com.NGO.libraryManagementSystem.Service.BookService;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final  CategoryService categoryService;
    private final BookMap bookMapper;

    public UserController(BookService bookService, AuthorService authorService
            , CategoryService categoryService, BookMap bookMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/books")
    public ResponseEntity<List<SavedBookDto>> retrieveBookList(){
        return ResponseEntity.ok(bookService.retrieveBooks());
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<?> retrieveBookById(@PathVariable Integer id){
        Optional<Book> book=bookService.retrieveBook(id);
        if (book.isEmpty()){
            return ResponseEntity.badRequest().body("book with this id not exist");
        }
        return ResponseEntity.ok(bookMapper.BookToBookDto(book.get()));
    }
    @PostMapping("/search")
    public ResponseEntity<List<SavedBookDto>> searchForBook(@RequestBody SearchDto searchDto){

        return ResponseEntity.ok(bookService.retrieveSearchBooks(searchDto));

    }

}



