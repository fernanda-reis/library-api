package com.projeto.library.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loans")
@Getter @Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date loanStart;
    private Date loanEnd;
    @ManyToOne
    private Patron patron;
    @ManyToMany
    private List<Book> books;

}
