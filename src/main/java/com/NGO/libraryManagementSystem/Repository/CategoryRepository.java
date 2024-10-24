package com.NGO.libraryManagementSystem.Repository;

import com.NGO.libraryManagementSystem.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByName(String category);


}
