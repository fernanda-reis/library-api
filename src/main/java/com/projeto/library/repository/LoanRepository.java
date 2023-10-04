package com.projeto.library.repository;

import com.projeto.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer>  {
    @Query(value = "SELECT * FROM LOANS WHERE USER_ID = :userId", nativeQuery = true)
    List<Loan> findAllByUser(Integer userId);

    @Query(value = "SELECT loan FROM Loan loan JOIN loan.books book WHERE book.id = :bookId")
    List<Loan> findAllByBook(Integer bookId);

}
