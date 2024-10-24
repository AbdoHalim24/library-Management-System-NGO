package com.NGO.libraryManagementSystem.Service.Imp;

import com.NGO.libraryManagementSystem.DTO.CategoryDto;
import com.NGO.libraryManagementSystem.DTO.SavedCategory;
import com.NGO.libraryManagementSystem.Entity.Category;
import com.NGO.libraryManagementSystem.Mapper.CategoryMap;
import com.NGO.libraryManagementSystem.Repository.CategoryRepository;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp  implements CategoryService {
    private  final CategoryRepository categoryRepository;

    private final CategoryMap categoryMap=new CategoryMap();


    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SavedCategory findCategoryByName(String category) {

        return categoryMap.CategorytoSavedCategory(categoryRepository.findByName(category)) ;
    }
    @Override
    public List<SavedCategory> RetrieveAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMap::CategorytoSavedCategory).collect(Collectors.toList());
    }


    @Override
    public SavedCategory createCategory(CategoryDto categoryDto) {
        Category category=categoryRepository.save(new Category(categoryDto.getName()));

        return new SavedCategory(category.getId(), category.getName()) ;
    }
    @Override
    public SavedCategory updateCategory(Integer id, CategoryDto categoryDto) {
        Category category=categoryRepository.findById(id).get();
        category.setName(categoryDto.getName());
       return categoryMap.CategorytoSavedCategory( categoryRepository.save(category));
    }
    @Override
    public Optional<Category> findByCategoryId(Integer id) {
        return categoryRepository.findById(id);
    }
    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findCategoryObjectByName(String category) {
        return categoryRepository.findByName(category);
    }

    @Override
    public SavedCategory RetrieveCategory(Integer id) {
        return categoryMap.CategorytoSavedCategory(categoryRepository.findById(id).get());
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
