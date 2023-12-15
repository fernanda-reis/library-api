package com.projeto.library.service;

import com.projeto.library.controller.dto.AuthorRequest;
import com.projeto.library.controller.dto.AuthorResponse;
import com.projeto.library.model.Author;
import com.projeto.library.repository.AuthorRepository;
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
class AuthorServiceUnitTest {

    AuthorRequest authorRequest;

    @InjectMocks
    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @BeforeEach
    public void setUp(){
        authorRequest = new AuthorRequest();
        authorRequest.setName("unit-test");
        authorRequest.setCountry("unit-test");
    }

    @Test
    public void AuthorService_SaveValidAuthor_ReturnAuthorResponse(){
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(new Author());

        AuthorResponse authorResponse = authorService.save(authorRequest);
        Assertions.assertNotNull(authorResponse);
    }

    @Test
    public void AuthorService_GetAll_ReturnListOfAuthorResponse(){
        Mockito.when(authorRepository.findAll()).thenReturn(List.of(new Author()));
        List<AuthorResponse> authors = authorService.getAll();
        Assertions.assertNotNull(authors);
        Assertions.assertFalse(authors.isEmpty());
    }

    @Test
    public void AuthorService_GetByValidId_ReturnAuthorResponse(){
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(Optional.of(new Author()));

        AuthorResponse authorResponse = authorService.getById(1);
        Assertions.assertNotNull(authorResponse);
    }

    @Test
    public void AuthorService_GetByValidName_ReturnAuthorResponse(){
        Mockito.when(authorRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Author()));

        AuthorResponse authorResponse = authorService.getByName("unit-test");
        Assertions.assertNotNull(authorResponse);
    }

    @Test
    public void AuthorService_GetAllByCountry_ReturnListOfAuthorResponse(){
        Mockito.when(authorRepository.findAllByCountry(Mockito.any())).thenReturn(List.of(new Author()));

        List<AuthorResponse> authors = authorService.getAllByCountry("unit-test");
        Assertions.assertNotNull(authors);
        Assertions.assertFalse(authors.isEmpty());
    }

    @Test
    public void AuthorService_Update_ReturnAuthorResponse(){
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(new Author());

        AuthorResponse author = authorService.update(1, authorRequest);
        Assertions.assertNotNull(author);
    }

    @Test
    public void AuthorService_Delete_WithSuccess(){
        authorService.delete(1);
    }

}