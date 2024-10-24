package com.NGO.libraryManagementSystem.DTO;

import com.NGO.libraryManagementSystem.Entity.Author;
import com.NGO.libraryManagementSystem.Entity.Category;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    @NotBlank(message = "the name must be not blank")
    @NotNull(message = "the name must be not null")
    private String name;

    private LocalDate publishedDate;

    private Integer authorId;
    @NotNull(message = "Category name must be not null")
    @NotBlank(message = "Category name must be not blank")
    private String category;

    public String getName() {
        return name;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }


    public void setCategory(String category) {
        this.category = category;
    }
}
