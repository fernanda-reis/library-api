package com.projeto.library.service;

import com.projeto.library.controller.dto.LoanRequest;
import com.projeto.library.controller.dto.LoanResponse;
import com.projeto.library.model.*;
import com.projeto.library.repository.BookRepository;
import com.projeto.library.repository.LoanRepository;
import com.projeto.library.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class LoanServiceUnitTest {

    LoanRequest loanRequest;
    Loan loan;
    @InjectMocks
    LoanService loanService;

    @Mock
    LoanRepository loanRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        loanRequest = new LoanRequest();
        loanRequest.setUserId(1);
        loanRequest.setBooksIds(List.of(1));

        loan = new Loan(1, new User(), List.of(new Book(1, "unit-test", 0, new Author(), new Category())));
    }

    @Test
    public void LoanService_SaveValidLoan_ReturnLoanResponse() {
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(new User()));
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(new Book()));
        Mockito.when(loanRepository.save(Mockito.any())).thenReturn(loan);

        LoanResponse loanResponse = loanService.save(loanRequest);
        Assertions.assertNotNull(loanResponse);
    }

    @Test
    public void LoanService_GetById_ReturnLoanResponse() {
        Mockito.when(loanRepository.findById(Mockito.any())).thenReturn(Optional.of(loan));
        LoanResponse loanResponse = loanService.getById(1);
        Assertions.assertNotNull(loanResponse);
    }

    @Test
    public void LoanService_FindAllByUser_ReturnListOfLoanResponse() {
        Mockito.when(loanRepository.findAllByUser(Mockito.any())).thenReturn(List.of(loan));
        List<LoanResponse> loans = loanService.findAllByUser(1);
        Assertions.assertNotNull(loans);
        Assertions.assertFalse(loans.isEmpty());
    }

    @Test
    public void LoanService_FindAllByBook_ReturnListOfLoanResponse() {
        Mockito.when(loanRepository.findAllByBook(Mockito.any())).thenReturn(List.of(loan));
        List<LoanResponse> loans = loanService.findAllByBook(1);
        Assertions.assertNotNull(loans);
        Assertions.assertFalse(loans.isEmpty());
    }

    @Test
    public void LoanService_GetAll_ReturnListOfLoanResponse() {
        Mockito.when(loanRepository.findAll()).thenReturn(List.of(loan));

        List<LoanResponse> loans = loanService.getAll(null, null);
        Assertions.assertNotNull(loans);
        Assertions.assertFalse(loans.isEmpty());
    }

    @Test
    public void LoanService_GetAllByUserId_ReturnListOfLoanResponse() {
        Mockito.when(loanRepository.findAllByUser(Mockito.any())).thenReturn(List.of(loan));

        List<LoanResponse> loans = loanService.getAll(1, null);
        Assertions.assertNotNull(loans);
        Assertions.assertFalse(loans.isEmpty());
    }

    @Test
    public void LoanService_GetAllByBookId_ReturnListOfLoanResponse() {
        Mockito.when(loanRepository.findAllByBook(Mockito.any())).thenReturn(List.of(loan));

        List<LoanResponse> loans = loanService.getAll(null, 1);
        Assertions.assertNotNull(loans);
        Assertions.assertFalse(loans.isEmpty());
    }
}