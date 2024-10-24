package com.NGO.libraryManagementSystem.Mapper;

import com.NGO.libraryManagementSystem.DTO.SavedCategory;
import com.NGO.libraryManagementSystem.Entity.Category;

public class CategoryMap {
   public SavedCategory CategorytoSavedCategory(Category category){
        return new SavedCategory(category.getId(), category.getName());
    }
}
