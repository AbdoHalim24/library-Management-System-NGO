package com.NGO.libraryManagementSystem.Repository;

import com.NGO.libraryManagementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByName(String author);
}
