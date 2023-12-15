package com.projeto.library.repository;

import com.projeto.library.model.Author;
import com.projeto.library.model.Book;
import com.projeto.library.model.Category;
import com.projeto.library.model.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorRepositoryUnitTest {
    @Autowired
    private AuthorRepository authorRepository;
    @BeforeAll
    public void setUp(){
        Author author = new Author(1, "Haruki Murakami", "Japan");
        authorRepository.save(author);
    }
    @Test
    public void AuthorRepository_findByName_ReturnAuthorByName(){
        String name = "Haruki Murakami";

        Author author = authorRepository.findByName(name).get();
        Assertions.assertEquals(author.getName(), name);
    }

    @Test
    public void AuthorRepository_findAllByCountry_ReturnListOfAuthorsByCountry(){
        String country = "Japan";

        List<Author> authors = authorRepository.findAllByCountry(country);
        Assertions.assertEquals(authors.get(0).getCountry(), country);
    }
}