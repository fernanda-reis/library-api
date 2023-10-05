package com.projeto.library.controller;

import com.projeto.library.controller.dto.LoanRequest;
import com.projeto.library.controller.dto.LoanResponse;
import com.projeto.library.service.LoanService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService service;

    @PostMapping
    public ResponseEntity<LoanResponse> save(@RequestBody LoanRequest loanRequest){
        LoanResponse loanResponse = service.save(loanRequest);
        return ResponseEntity.created(URI.create("/loan/"+loanResponse.getId())).body(loanResponse);
    }


    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAll(
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "bookId", required = false) Integer bookId
    ){
        return ResponseEntity.ok(service.getAll(userId, bookId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getById(id));
    }
}
