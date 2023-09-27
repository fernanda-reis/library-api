package com.projeto.library.controller.dto;

import lombok.Getter;

@Getter
public class BookRequest {
private String name;
private String country;
private Integer year;
private Integer authorId;
private Integer categoryId;

}
