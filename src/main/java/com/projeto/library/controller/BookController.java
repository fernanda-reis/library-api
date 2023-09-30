package com.projeto.library.controller;

import com.projeto.library.controller.dto.BookRequest;
import com.projeto.library.controller.dto.BookResponse;
import com.projeto.library.model.Book;
import com.projeto.library.service.BookService;
import com.projeto.library.utils.BookConverter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService service;

    @PostMapping()
    public ResponseEntity<BookResponse> save(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = service.save(bookRequest);
        return ResponseEntity.created(URI.create("/book/" + bookResponse.getId())).body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BookResponse> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<BookResponse> getByAuthorId(@PathVariable Integer authorId) {
        return ResponseEntity.ok(service.getByAuthorId(authorId));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<BookResponse> getByYearOfRelease(@PathVariable Integer yearOfRelease) {
        return ResponseEntity.ok(service.getByYearOfRelease(yearOfRelease));
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    public ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest){
        return ResponseEntity.ok(service.udpate(bookRequest));
    }

    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
