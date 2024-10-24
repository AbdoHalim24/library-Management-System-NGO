package com.NGO.libraryManagementSystem.Service.Imp;

import com.NGO.libraryManagementSystem.DTO.BookDto;
import com.NGO.libraryManagementSystem.DTO.SavedBookDto;
import com.NGO.libraryManagementSystem.DTO.SearchDto;
import com.NGO.libraryManagementSystem.Entity.Author;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Entity.Category;
import com.NGO.libraryManagementSystem.Mapper.BookMap;
import com.NGO.libraryManagementSystem.Repository.BookRepository;
import com.NGO.libraryManagementSystem.Service.AuthorService;
import com.NGO.libraryManagementSystem.Service.BookService;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;
    private final BookMap bookMapper;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImp(BookRepository bookRepository, BookMap bookMapper, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorService = authorService;
        this.categoryService = categoryService;

    }

    @Override
    public List<SavedBookDto> retrieveBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::BookToSavedBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> retrieveBook(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public SavedBookDto createBook(BookDto bookDto) {
        Book book= bookMapper.BookDtoToBook(bookDto);
        return bookMapper.BookToSavedBookDto(bookRepository.save(book));
    }

    @Override
    public SavedBookDto updateBook(Integer id, BookDto bookDto) {
        Book book=bookRepository.findById(id).get();
        Author author=authorService.RetrieveAuthorById(bookDto.getAuthorId()).get();
        book.setAuthor(author);
        Category category=categoryService.findCategoryObjectByName(bookDto.getCategory());
        book.setCategory(category);
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setName(bookDto.getName());
        return bookMapper.BookToSavedBookDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<SavedBookDto> retrieveSearchBooks(SearchDto searchDto) {

        if (searchDto.getAuthor()!=null){
            if (searchDto.getAuthor().isBlank() ){
                searchDto.setAuthor(null);
            }
        }
        if (searchDto.getCategory()!=null){
            if (searchDto.getCategory().isBlank()){
                searchDto.setCategory(null);
            }
        }


        Integer authorId,categoryId;

        if (searchDto.getAuthor()==null){
            authorId=null;
        }
        else {
            authorId=authorService.RetrieveAuthorByName(searchDto.getAuthor()).getId();
        }
        if (searchDto.getCategory()==null){
            categoryId=null;
        }
        else {
            categoryId=categoryService.findCategoryByName(searchDto.getCategory()).getId();
        }


        List<Book> bookList=bookRepository.retrieveSearchBooks(authorId,categoryId);

        return bookList.stream().map(bookMapper::BookToSavedBookDto).collect(Collectors.toList());
    }

    @Override
    public List<SavedBookDto> retrieveAuthorBooks(Integer id) {
        List<Book> bookList=bookRepository.findByAuthorId(id);
        return bookList.stream().map(bookMapper::BookToSavedBookDto).collect(Collectors.toList());
    }
}
