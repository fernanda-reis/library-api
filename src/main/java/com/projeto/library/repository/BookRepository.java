package com.projeto.library.repository;

import com.projeto.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);

    @Query(value = "SELECT * FROM BOOKS WHERE AUTHOR_ID = :authorId", nativeQuery = true)
    List<Book> findByAuthorId(Integer authorId);

    List<Book> findByYearOfRelease(Integer year);

}
