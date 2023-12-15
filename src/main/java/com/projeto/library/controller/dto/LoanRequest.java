package com.projeto.library.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LoanRequest {
    private Integer userId;

    private List<Integer> booksIds;
}
