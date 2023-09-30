package com.projeto.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookResponse {
    private Integer id;
    private String name;
    private Integer yearOfRelease;
    private AuthorResponse author;
    private CategoryResponse category;
}
