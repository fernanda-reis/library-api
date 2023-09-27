package com.projeto.library.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
@Entity
@Table(name = "patrons")
@Getter @Setter
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private Integer password;

}
