package com.NGO.libraryManagementSystem.Service.Imp;

import com.NGO.libraryManagementSystem.DTO.AuthorDto;
import com.NGO.libraryManagementSystem.DTO.SavedAuthorDto;
import com.NGO.libraryManagementSystem.Entity.Author;
import com.NGO.libraryManagementSystem.Mapper.AuthorMap;
import com.NGO.libraryManagementSystem.Repository.AuthorRepository;
import com.NGO.libraryManagementSystem.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImp implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMap authorMapper;

    public AuthorServiceImp(AuthorRepository authorRepository, AuthorMap authorMap) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMap;
    }

    @Override
    public Author RetrieveAuthorByName(String author) {

        return authorRepository.findByName(author);
    }

    @Override
    public List<SavedAuthorDto> RetrieveAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::AuthorToSavedAuthorDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Author> findAuthorById(Integer id) {
        return authorRepository.findById(id);
    }

    @Override
    public SavedAuthorDto createAuthor(AuthorDto authorDto) {
        Author author=new Author();
        author.setName(authorDto.getName());
        Author author1 =authorRepository.save(author);
        SavedAuthorDto savedAuthorDto=new SavedAuthorDto(author1.getId(),author1.getName());

        return savedAuthorDto;
    }

    @Override
    public SavedAuthorDto updateAuthor(Integer id, AuthorDto authorDto) {
        Optional<Author> author=authorRepository.findById(id);

        author.get().setName(authorDto.getName());
        return authorMapper.AuthorToSavedAuthorDto( authorRepository.save(author.get()));
    }

    @Override
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findAuthorByName(String author) {

        return authorRepository.findByName(author);
    }

    public Optional<Author> RetrieveAuthorById(Integer id) {
        return authorRepository.findById(id);
    }
}
