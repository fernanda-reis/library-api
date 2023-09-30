package com.projeto.library.controller.dto;

import lombok.Getter;

@Getter
public class BookRequest {
private String name;
private Integer yearOfRelease;
private Integer authorId;
private Integer categoryId;

}
