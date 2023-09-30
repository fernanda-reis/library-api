package com.projeto.library.repository;

import com.projeto.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);

    @Query(value = "SELECT * FROM BOOKS WHERE AUTHOR_ID = :authorId", nativeQuery = true)
    Optional<Book> findByAuthorId(Integer authorId);
    Optional<Book> findByYearOfRelease(Integer year);

}
