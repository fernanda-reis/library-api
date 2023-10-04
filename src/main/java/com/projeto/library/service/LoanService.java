package com.projeto.library.service;

import java.util.ArrayList;
import java.util.List;
import com.projeto.library.controller.dto.LoanRequest;
import com.projeto.library.controller.dto.LoanResponse;
import com.projeto.library.model.Book;
import com.projeto.library.model.Loan;
import com.projeto.library.model.User;
import com.projeto.library.repository.BookRepository;
import com.projeto.library.repository.LoanRepository;
import com.projeto.library.repository.UserRepository;
import com.projeto.library.utils.LoanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    public LoanResponse save(LoanRequest loanRequest) {
        User user = userRepository.findById(loanRequest.getUserId()).get();

        List<Book> books = new ArrayList<>();
        List<Integer> booksIds = loanRequest.getBooksIds();

        for(Integer id : booksIds) {
            books.add(bookRepository.findById(id).get());
        }

        Loan loan = LoanConverter.toEntity(loanRequest, user, books);
        return LoanConverter.toResponse(loanRepository.save(loan));
    }

    public LoanResponse getById(Integer id) {
        return LoanConverter.toResponse(loanRepository.findById(id).get());
    }

    public List<LoanResponse> findAllByUser(Integer userId) {
        return LoanConverter.toResponseList(loanRepository.findAllByUser(userId));
    }

    public List<LoanResponse> findAllByBook(Integer bookId) {
        return LoanConverter.toResponseList(loanRepository.findAllByBook(bookId));
    }

    public List<LoanResponse> getAll(Integer userId, Integer bookId){
        if( userId != null ){
            return findAllByUser(userId);
        } else if(bookId != null){
            return findAllByBook(bookId);
        } else {
            return LoanConverter.toResponseList(loanRepository.findAll());
        }
    }


}
