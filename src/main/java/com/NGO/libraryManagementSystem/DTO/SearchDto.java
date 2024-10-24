package com.NGO.libraryManagementSystem.DTO;

public class SearchDto {
    private String author;
    private String category;

    public SearchDto(String author, String category) {
        this.author = author;
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
