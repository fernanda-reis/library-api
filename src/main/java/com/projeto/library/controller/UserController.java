package com.projeto.library.controller;

import com.projeto.library.controller.dto.UserRequest;
import com.projeto.library.controller.dto.UserResponse;
import com.projeto.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest){
        UserResponse userResponse = service.save(userRequest);
        return ResponseEntity.created(URI.create("/user/" + userResponse.getId())).body(userResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
        return  ResponseEntity.ok(service.getByEmail(email));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserResponse>> getAllByName(@PathVariable String name, @PathVariable Integer id){
        return ResponseEntity.ok(service.getAllByName(name));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Integer id,
            @RequestBody UserRequest userRequest
    ){
        return  ResponseEntity.ok(service.update(id, userRequest));
    }

}
