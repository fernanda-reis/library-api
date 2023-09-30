package com.projeto.library.utils;

import com.projeto.library.controller.dto.AuthorRequest;
import com.projeto.library.controller.dto.AuthorResponse;
import com.projeto.library.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter {
    public static Author toEntity(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setCountry(authorRequest.getCountry());
        return author;
    }

    public static AuthorResponse toResponse(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setCountry(author.getCountry());
        return authorResponse;
    }

    public static List<AuthorResponse> toResponseList(List<Author> authors) {
        List<AuthorResponse> authorResponses = new ArrayList<>();

        for(Author author : authors) {
            authorResponses.add(toResponse(author));
        }

        return authorResponses;
    }
}
