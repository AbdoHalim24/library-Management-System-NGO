package com.NGO.libraryManagementSystem.Controller;

import com.NGO.libraryManagementSystem.DTO.CategoryDto;
import com.NGO.libraryManagementSystem.DTO.SavedCategory;
import com.NGO.libraryManagementSystem.Entity.Book;
import com.NGO.libraryManagementSystem.Entity.Category;
import com.NGO.libraryManagementSystem.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<SavedCategory>> retrieveAllCategories(){
        return ResponseEntity.ok(categoryService.RetrieveAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveCategory(@PathVariable Integer id){
        Optional<Category> category=categoryService.findCategoryById(id);
        if (category.isEmpty()){
            return ResponseEntity.badRequest().body("category With this id not found");
        }
        return ResponseEntity.ok(categoryService.RetrieveCategory(id));
    }
    @PostMapping
    public ResponseEntity<?> addCategory(@Validated @RequestBody CategoryDto categoryDto){
        if (categoryService.findCategoryObjectByName(categoryDto.getName())==null){
            SavedCategory category=categoryService.createCategory(categoryDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(category.getId())
                    .toUri();
            return ResponseEntity.created(location).body(category);
        }
        return ResponseEntity.badRequest().body("this category is exist");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@Validated @RequestBody CategoryDto categoryDto){
        if (categoryService.findCategoryObjectByName(categoryDto.getName())!=null){
            return ResponseEntity.badRequest().body("this category is exist");
        }
        Optional<Category> category =categoryService.findByCategoryId(id);
        if (category.isEmpty()){
            return ResponseEntity.badRequest().body("id not exist");
        }
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        Optional<Category> category =categoryService.findByCategoryId(id);

        if (category.isEmpty()){
            return ResponseEntity.badRequest().body("id not exist");
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
