package com.projeto.library.service;

import com.projeto.library.controller.dto.BookRequest;
import com.projeto.library.controller.dto.BookResponse;
import com.projeto.library.model.Author;
import com.projeto.library.model.Book;
import com.projeto.library.model.Category;
import com.projeto.library.repository.AuthorRepository;
import com.projeto.library.repository.BookRepository;
import com.projeto.library.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class BookServiceUnitTest {

    BookRequest bookRequest;
    Book book;

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        bookRequest = new BookRequest();
        bookRequest.setAuthorId(1);
        bookRequest.setCategoryId(1);
        bookRequest.setName("unit-test");
        bookRequest.setYearOfRelease(0);

        book = new Book(1, "unit-test", 0, new Author(), new Category());
    }
    @Test
    public void BookService_SaveValidBook_ReturnBookResponse(){
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(Optional.of(new Author()));
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(new Category()));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        BookResponse bookResponse = bookService.save(bookRequest);
        Assertions.assertNotNull(bookResponse);
    }

    @Test
    public void BookService_GetByValidId_ReturnBookResponse(){

        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
        BookResponse book = bookService.getById(1);
        Assertions.assertNotNull(book);
    }

    @Test
    public void BookService_GetByValidName_ReturnBookResponse(){
        Mockito.when(bookRepository.findByName(Mockito.any())).thenReturn(Optional.of(book));
        BookResponse book = bookService.getByName("unit-test");
        Assertions.assertNotNull(book);
    }

    @Test
    public void BookService_GetByValidAuthorId_ReturnListOfBookResponse(){
        Mockito.when(bookRepository.findByAuthorId(Mockito.any())).thenReturn(List.of(book));
        List<BookResponse> books = bookService.getByAuthorId(1);
        Assertions.assertNotNull(books);
        Assertions.assertFalse(books.isEmpty());
    }

    @Test
    public void BookService_GetByValidYearOfRelease_ReturnListOfBookResponse(){
        Mockito.when(bookRepository.findByYearOfRelease(Mockito.any())).thenReturn(List.of(book));
        List<BookResponse> books = bookService.getByYearOfRelease(0);
        Assertions.assertNotNull(books);
        Assertions.assertFalse(books.isEmpty());
    }

    @Test
    public void BookService_GetAll_ReturnListOfBookResponse(){
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book));
        List<BookResponse> books = bookService.getAll();
        Assertions.assertNotNull(books);
        Assertions.assertFalse(books.isEmpty());
    }

    @Test
    public void BookService_Update_ReturnBook(){
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(Optional.of(new Author()));
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(new Category()));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        BookResponse book = bookService.udpate(1, bookRequest);
        Assertions.assertNotNull(book);
    }

    @Test
    public void BookService_Delete_WithSuccess(){
        bookService.delete(1);
    }



}