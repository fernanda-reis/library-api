package com.projeto.library.utils;

import com.projeto.library.controller.dto.BookRequest;
import com.projeto.library.controller.dto.BookResponse;
import com.projeto.library.model.Author;
import com.projeto.library.model.Book;
import com.projeto.library.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BookConverter {
    public static Book toEntity(BookRequest bookRequest, Author author, Category category){
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setYearOfRelease(bookRequest.getYearOfRelease());
        book.setAuthor(author);
        book.setCategory(category);
        return book;
    }

    public static BookResponse toResponse(Book book){
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setYearOfRelease(book.getYearOfRelease());
        bookResponse.setAuthor(AuthorConverter.toResponse(book.getAuthor()));
        bookResponse.setCategory(CategoryConverter.toResponse(book.getCategory()));
        return bookResponse;
    }

    public static List<BookResponse> toResponseList(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book : books) {
            bookResponses.add(toResponse(book));
        }
        return bookResponses;
    }
}
