package com.NGO.libraryManagementSystem.Service;

import com.NGO.libraryManagementSystem.DTO.CategoryDto;
import com.NGO.libraryManagementSystem.DTO.SavedCategory;
import com.NGO.libraryManagementSystem.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    SavedCategory findCategoryByName(String category);

    List<SavedCategory> RetrieveAllCategories();

    SavedCategory createCategory(CategoryDto categoryDto);

    SavedCategory updateCategory(Integer id, CategoryDto categoryDto);

    Optional<Category> findByCategoryId(Integer id);

    void deleteCategory(Integer id);

    Category findCategoryObjectByName(String category);

    SavedCategory RetrieveCategory(Integer id);

    Optional<Category> findCategoryById(Integer id);
}
