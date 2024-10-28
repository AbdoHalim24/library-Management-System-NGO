package com.NGO.libraryManagementSystem.Service.Imp;

import com.NGO.libraryManagementSystem.DTO.CategoryDto;
import com.NGO.libraryManagementSystem.DTO.SavedCategory;
import com.NGO.libraryManagementSystem.Entity.Author;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Entity.Category;
import com.NGO.libraryManagementSystem.Mapper.CategoryMap;
import com.NGO.libraryManagementSystem.Repository.BookRepository;
import com.NGO.libraryManagementSystem.Repository.CategoryRepository;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp  implements CategoryService {
    private  final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    private final CategoryMap categoryMap=new CategoryMap();


    public CategoryServiceImp(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
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
    @Transactional
    public void deleteCategory(Integer id) {
        Category category=categoryRepository.findById(id).get();
         List<Book> books=category.getBookList();
         for (Book book : books){
             book.setCategory(null);
         }
        bookRepository.saveAll(books);
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
