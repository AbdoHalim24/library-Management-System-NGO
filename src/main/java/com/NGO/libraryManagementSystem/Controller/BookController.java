package com.NGO.libraryManagementSystem.Controller;
import com.NGO.libraryManagementSystem.DTO.BookDto;
import com.NGO.libraryManagementSystem.DTO.SavedAuthorDto;
import com.NGO.libraryManagementSystem.DTO.SavedBookDto;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Mapper.BookMap;
import com.NGO.libraryManagementSystem.Service.AuthorService;
import com.NGO.libraryManagementSystem.Service.BookService;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/book")
public class BookController {
    private final BookService bookService ;
    private  final BookMap bookMaper;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, BookMap bookMap, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.bookMaper = bookMap;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<SavedBookDto>> retrieveAllBooks(){
        List<SavedBookDto> bookList=bookService.retrieveBooks();
        return ResponseEntity.ok(bookList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveAllBooks(@PathVariable Integer id){
        Optional<Book> book=bookService.retrieveBook(id);
        if (book.isPresent()){
             return  ResponseEntity.ok(bookMaper.BookToBookDto(book.get()));
        }
        else {
          return ResponseEntity.badRequest().body("book with this id not exist");
        }
    }
    @PostMapping
    public ResponseEntity<?> createBook(@Validated @RequestBody BookDto bookDto){
        if (authorService.RetrieveAuthorById(bookDto.getAuthorId()).isEmpty()){
            return  ResponseEntity.badRequest().body("Author Name not exist");
        }
        if (categoryService.findCategoryObjectByName(bookDto.getCategory())==null){
            return  ResponseEntity.badRequest().body("Category Name not exist");
        }
        SavedBookDto book =bookService.createBook(bookDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).body(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id,@RequestBody BookDto bookDto){
        Optional<Book> book=bookService.retrieveBook(id);
        if (book.isEmpty()){
            return ResponseEntity.badRequest().body("book with this id not exist");
        }
        if (authorService.RetrieveAuthorById(bookDto.getAuthorId()).isEmpty()){
            return  ResponseEntity.badRequest().body("Author Name not exist");
        }

        return ResponseEntity.ok(bookService.updateBook(id,bookDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id){
        Optional<Book> book=bookService.retrieveBook(id);
        if (book.isEmpty()){
            return ResponseEntity.badRequest().body("book with this id not exist");
        }
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


}
