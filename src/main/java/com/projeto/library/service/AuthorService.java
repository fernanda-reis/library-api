package com.projeto.library.service;

import com.projeto.library.controller.dto.AuthorRequest;
import com.projeto.library.controller.dto.AuthorResponse;
import com.projeto.library.model.Author;
import com.projeto.library.repository.AuthorRepository;
import com.projeto.library.utils.AuthorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public AuthorResponse save(AuthorRequest authorRequest){
        Author author = AuthorConverter.toEntity(authorRequest);
        return AuthorConverter.toResponse(repository.save(author));
    }

    public List<AuthorResponse> getAll(){
        return AuthorConverter.toResponseList(repository.findAll());
    }

    public AuthorResponse getById(Integer id){
        return AuthorConverter.toResponse(repository.findById(id).get());
    }

    public AuthorResponse getByName(String name) {
        return AuthorConverter.toResponse(repository.findByName(name).get());
    }

    public List<AuthorResponse> getAllByCountry(String country){
        return AuthorConverter.toResponseList(repository.findAllByCountry(country));
    }

    public AuthorResponse update(Integer id, AuthorRequest authorRequest) {
        Author author = AuthorConverter.toEntity(authorRequest);
        author.setId(id);
        return AuthorConverter.toResponse(repository.save(author));
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

}
