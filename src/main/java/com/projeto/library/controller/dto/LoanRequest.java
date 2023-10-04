package com.projeto.library.controller.dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class LoanRequest {
    private Integer userId;

    private List<Integer> booksIds;
}
