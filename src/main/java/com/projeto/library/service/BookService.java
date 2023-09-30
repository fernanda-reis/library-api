package com.projeto.library.service;

import com.projeto.library.controller.dto.BookRequest;
import com.projeto.library.controller.dto.BookResponse;
import com.projeto.library.model.Author;
import com.projeto.library.model.Book;
import com.projeto.library.model.Category;
import com.projeto.library.repository.AuthorRepository;
import com.projeto.library.repository.BookRepository;
import com.projeto.library.repository.CategoryRepository;
import com.projeto.library.utils.BookConverter;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public BookResponse save(BookRequest bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId()).get();
        Category category = categoryRepository.findById(bookRequest.getCategoryId()).get();

        Book book = BookConverter.toEntity(bookRequest, author, category);
        return BookConverter.toResponse(bookRepository.save(book));
    }

    public BookResponse getById(Integer id) {
        return BookConverter.toResponse(bookRepository.findById(id).get());
    }

    public BookResponse getByName(String name) {
        return BookConverter.toResponse(bookRepository.findByName(name).get());
    }
    public BookResponse getByAuthorId(Integer authorId) {
        return BookConverter.toResponse(bookRepository.findByAuthorId(authorId).get());
    }
    public BookResponse getByYearOfRelease(Integer year) {
        return BookConverter.toResponse(bookRepository.findByYearOfRelease(year).get());
    }
    public List<BookResponse> getAll(){
        return BookConverter.toResponseList(bookRepository.findAll());
    }

    public BookResponse udpate(BookRequest bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId()).get();
        Category category = categoryRepository.findById(bookRequest.getCategoryId()).get();

        Book book = BookConverter.toEntity(bookRequest, author, category);
        return BookConverter.toResponse(bookRepository.save(book));
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
}
