package com.projeto.library.controller;

import com.projeto.library.controller.dto.AuthorRequest;
import com.projeto.library.controller.dto.AuthorResponse;
import com.projeto.library.model.Author;
import com.projeto.library.service.AuthorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService service;

    @PostMapping
    public ResponseEntity<AuthorResponse> save(@RequestBody AuthorRequest authorRequest){
        AuthorResponse authorResponse = service.save(authorRequest);
        return ResponseEntity.created(URI.create("/author/" + authorResponse.getId())).body(authorResponse);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorResponse> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping("country/{country}")
    public ResponseEntity<List<AuthorResponse>> getAllByCountry(@PathVariable String country) {
        return ResponseEntity.ok(service.getAllByCountry(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Integer id, @RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(service.update(id, authorRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

