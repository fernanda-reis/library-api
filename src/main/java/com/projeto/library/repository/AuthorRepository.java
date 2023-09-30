package com.projeto.library.repository;

import com.projeto.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);

    List<Author> findAllByCountry(String country);
}
