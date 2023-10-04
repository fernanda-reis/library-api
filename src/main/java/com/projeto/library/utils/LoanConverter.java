package com.projeto.library.utils;

import com.projeto.library.controller.dto.LoanRequest;
import com.projeto.library.controller.dto.LoanResponse;
import com.projeto.library.model.Book;
import com.projeto.library.model.Loan;
import com.projeto.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoanConverter {
    public static Loan toEntity(LoanRequest loanRequest, User user, List<Book> books) {
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBooks(books);
        return loan;
    }

    public static LoanResponse toResponse(Loan loan) {
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setId(loan.getId());

        loanResponse.setUser(UserConverter.toResponse(loan.getUser()));
        loanResponse.setBooks(BookConverter.toResponseList(loan.getBooks()));
        return loanResponse;
    }

    public static List<LoanResponse> toResponseList(List<Loan> loans){
        List<LoanResponse> loanResponses = new ArrayList<>();
        for(Loan loan : loans) {
            loanResponses.add(toResponse(loan));
        }

        return loanResponses;
    }

}
