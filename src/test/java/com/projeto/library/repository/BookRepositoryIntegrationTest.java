package com.projeto.library.repository;

import com.projeto.library.model.Author;
import com.projeto.library.model.Book;
import com.projeto.library.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookRepositoryIntegrationTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    public void setUp(){
        Author author = new Author(1, "Haruki Murakami", "Japan");
        authorRepository.save(author);

        Category category = new Category(1, "Fiction");
        categoryRepository.save(category);

        Book book = new Book(1, "1Q84", 2009, author, category);
        bookRepository.save(book);
    }

    @Test
    public void BookRepository_findByName_ReturnBookByName(){
        String name = "1Q84";

        Book book = bookRepository.findByName(name).get();
        Assertions.assertEquals(book.getName(), name);
    }

    @Test
    public void BookRepository_findByAuthorId_ReturnListOfBooksByAuthorId(){
        Integer authorId = 1;

        List<Book> books = bookRepository.findByAuthorId(authorId);
        Assertions.assertTrue(books.size() == 1);
        Assertions.assertEquals(books.get(0).getAuthor().getId(), authorId);

    }
    @Test
    public void BookRepository_findByYearOfRelease_ReturnBookByYearOfRelease(){
        Integer yearOfRelease = 2009;

        List<Book> books = bookRepository.findByYearOfRelease(yearOfRelease);
        Assertions.assertTrue(books.size() == 1);
        Assertions.assertEquals(books.get(0).getYearOfRelease(), yearOfRelease);
    }
}