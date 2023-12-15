package com.projeto.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
private String name;
private Integer yearOfRelease;
private Integer authorId;
private Integer categoryId;

}
