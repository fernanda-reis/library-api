package com.projeto.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.library.controller.dto.AuthorResponse;
import com.projeto.library.controller.dto.BookRequest;
import com.projeto.library.controller.dto.BookResponse;
import com.projeto.library.controller.dto.CategoryResponse;
import com.projeto.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    BookRequest bookRequest;
    BookResponse bookResponse;

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        bookRequest = new BookRequest();
        bookRequest.setName("unit-test");
        bookRequest.setYearOfRelease(0);
        bookRequest.setAuthorId(1);
        bookRequest.setCategoryId(1);

        bookResponse = new BookResponse();
        bookResponse.setId(1);
        bookResponse.setName("unit-test");
        bookResponse.setYearOfRelease(0);

        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(1);
        authorResponse.setName("unit-test");
        authorResponse.setCountry("unit-test");
        bookResponse.setAuthor(authorResponse);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(1);
        categoryResponse.setName("unit-test");
        bookResponse.setCategory(categoryResponse);
    }

    @Test
    public void BookController_SaveValidBook_ReturnSuccess() throws Exception {
        Mockito.when(bookService.save(Mockito.any())).thenReturn(bookResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/book")
                .content(objectMapper.writeValueAsString(bookRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void BookController_GetByValidId_ReturnSuccess_AndBookResponseData() throws Exception {
        Mockito.when(bookService.getById(Mockito.any())).thenReturn(bookResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/" + bookResponse.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id")
                        .value(bookResponse.getId())
        );
    }

    @Test
    public void BookController_GetByValidName_ReturnSuccess_AndBookResponseData() throws Exception {
        Mockito.when(bookService.getByName(Mockito.any())).thenReturn(bookResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/name/" + bookResponse.getName())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name")
                        .value(bookResponse.getName())
        );
    }

    @Test
    public void BookController_GetByValidAuthorId_ReturnSuccess_AndListOfBookResponseData() throws Exception {
        Mockito.when(bookService.getByAuthorId(Mockito.any())).thenReturn(List.of(bookResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/author/" + bookResponse.getAuthor().getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].author.id")
                        .value(bookResponse.getAuthor().getId())
        );
    }

    @Test
    public void BookController_GetByValidYearOfRelease_ReturnSuccess_AndListOfBookResponseData() throws Exception {
        Mockito.when(bookService.getByYearOfRelease(Mockito.any())).thenReturn(List.of(bookResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/year/" + bookResponse.getYearOfRelease())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].yearOfRelease")
                        .value(bookResponse.getYearOfRelease())
        );
    }

    @Test
    public void BookController_GetAll_ReturnSuccess_AndListOfBookResponseData() throws Exception {
        Mockito.when(bookService.getAll()).thenReturn(List.of(bookResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id")
                        .value(bookResponse.getId())
        );
    }

    @Test
    public void BookController_Update_ReturnSuccess() throws Exception {
        Mockito.when(bookService.udpate(Mockito.any(), Mockito.any())).thenReturn(bookResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/book/" + bookResponse.getId())
                .content(objectMapper.writeValueAsString(bookRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    }