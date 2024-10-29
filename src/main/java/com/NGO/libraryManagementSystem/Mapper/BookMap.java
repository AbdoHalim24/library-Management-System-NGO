package com.NGO.libraryManagementSystem.Mapper;


import com.NGO.libraryManagementSystem.DTO.BookDto;
import com.NGO.libraryManagementSystem.DTO.SavedBookDto;
import com.NGO.libraryManagementSystem.DTO.SavedCategory;
import com.NGO.libraryManagementSystem.Entity.Author;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Entity.Category;
import com.NGO.libraryManagementSystem.Service.AuthorService;
import com.NGO.libraryManagementSystem.Service.BookService;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class BookMap {
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookMap(AuthorService authorService, CategoryService categoryService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    public Book BookDtoToBook(BookDto bookDto){
        Book book=new Book();
        Author author=authorService.findAuthorById(bookDto.getAuthorId()).get();
        Category category=categoryService.findCategoryObjectByName(bookDto.getCategory());
        book.setCategory(category);
        book.setAuthor(author);


        book.setPublishedDate(bookDto.getPublishedDate());
        book.setName(bookDto.getName());
        return book;
    }
    public BookDto BookToBookDto(Book book){
        BookDto bookDto=new BookDto();
        bookDto.setAuthorId(book.getAuthor().getId());
        bookDto.setCategory(book.getCategory().getName());
        bookDto.setName(book.getName());
        bookDto.setPublishedDate(book.getPublishedDate());
        return bookDto;
    }
    public SavedBookDto BookToSavedBookDto(Book book){
        SavedBookDto savedBookDto=new SavedBookDto();
        savedBookDto.setId(book.getId());
        savedBookDto.setName(book.getName());
        savedBookDto.setPublishedDate(book.getPublishedDate());
        if (book.getAuthor()==null){
            savedBookDto.setAuthor(null);
        }
        else{
            savedBookDto.setAuthor(book.getAuthor().getName());
        }
        if (book.getCategory()==null){
            savedBookDto.setCategory(null);
        }
        else {
            savedBookDto.setCategory(book.getCategory().getName());
        }
        return savedBookDto;
    }
}
