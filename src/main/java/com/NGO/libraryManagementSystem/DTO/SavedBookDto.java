package com.NGO.libraryManagementSystem.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SavedBookDto {

    private Integer id;
    private String name;

    private LocalDate publishedDate;

    private String author;

    private String category;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
