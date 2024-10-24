package com.NGO.libraryManagementSystem.Repository;

import com.NGO.libraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT * FROM book WHERE" +
            " (:aid IS NULL OR author_id = :aid) " +
            "AND (:cid IS NULL OR category_id = :cid)",
            nativeQuery = true)
    List<Book> retrieveSearchBooks(Integer aid, Integer cid);


    @Query(value = "select  * from book where author_id=?1",nativeQuery = true)
    List<Book> findByAuthorId(Integer id);
}
