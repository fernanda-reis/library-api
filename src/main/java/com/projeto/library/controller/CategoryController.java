package com.projeto.library.controller;

import com.projeto.library.controller.dto.CategoryRequest;
import com.projeto.library.controller.dto.CategoryResponse;
import com.projeto.library.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = service.save(categoryRequest);
        return ResponseEntity.created(URI.create("/category/" + categoryResponse.getId())).body(categoryResponse);
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponse> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(service.update(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
