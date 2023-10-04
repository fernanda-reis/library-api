package com.projeto.library.controller.dto;

import com.projeto.library.model.Book;
import com.projeto.library.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LoanResponse {
    private Integer id;

    private UserResponse user;
    private List<BookResponse> books;
}
