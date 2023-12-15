package com.projeto.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.library.controller.dto.*;
import com.projeto.library.service.LoanService;
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
class LoanControllerIntegrationTest {

    LoanRequest loanRequest;
    LoanResponse loanResponse;

    @MockBean
    private LoanService loanService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        loanRequest = new LoanRequest();
        loanRequest.setUserId(1);
        loanRequest.setBooksIds(List.of(1));

        loanResponse = new LoanResponse();
        loanResponse.setId(1);

        UserResponse user = new UserResponse();
        user.setId(1);
        user.setName("Maria");
        user.setEmail("maria@email.com");
        loanResponse.setUser(user);

        BookResponse book = new BookResponse();
        book.setId(1);
        book.setName("unit-test");
        book.setYearOfRelease(0);
        book.setAuthor(new AuthorResponse());
        book.setCategory(new CategoryResponse());
        loanResponse.setBooks(List.of(book));
    }

    @Test
    public void LoanController_SaveValidLoan_ReturnSuccess() throws Exception {
        Mockito.when(loanService.save(Mockito.any())).thenReturn(loanResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/loan")
                .content(objectMapper.writeValueAsString(loanRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void LoanController_GetAll_ReturnSuccess_AndListOfLoanResponseData() throws Exception {
        Mockito.when(loanService.getAll(Mockito.any(), Mockito.any())).thenReturn(List.of(loanResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/loan?userId=" +loanRequest.getUserId()+"&bookId="+loanRequest.getBooksIds().get(0))
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].user.id")
                        .value(loanResponse.getUser().getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].books[0].id")
                        .value(loanResponse.getBooks().get(0).getId())
        );
    }

    @Test
    public void LoanController_GetByValidId_ReturnSuccess_AndLoanResponseData() throws Exception {
        Mockito.when(loanService.getById(Mockito.any())).thenReturn(loanResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/" + loanResponse.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id")
                        .value(loanResponse.getId())
        );
    }
}