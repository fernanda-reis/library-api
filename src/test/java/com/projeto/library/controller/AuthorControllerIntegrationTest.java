package com.projeto.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.library.controller.dto.AuthorRequest;
import com.projeto.library.controller.dto.AuthorResponse;
import com.projeto.library.service.AuthorService;
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
class AuthorControllerIntegrationTest {
    AuthorRequest authorRequest;
    AuthorResponse authorResponse;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        authorRequest = new AuthorRequest();
        authorRequest.setName("unit-test");
        authorRequest.setCountry("unit-test");

        authorResponse = new AuthorResponse();
        authorResponse.setId(1);
        authorResponse.setName(authorRequest.getName());
        authorResponse.setCountry(authorRequest.getCountry());
    }

    @Test
    public void AuthorController_SaveValidAuthor_ReturnSuccess() throws Exception {
        Mockito.when(authorService.save(Mockito.any())).thenReturn(authorResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .content(objectMapper.writeValueAsString(authorRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void AuthorController_GetAll_ReturnSuccess_AndListOfAuthorResponseData() throws Exception {
        Mockito.when(authorService.getAll()).thenReturn(List.of(authorResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/author")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id")
                        .value(authorResponse.getId())
        );
    }

    @Test
    public void AuthorController_GetByValidId_ReturnSuccess_AndAuthorResponseData() throws Exception {
        Mockito.when(authorService.getById(Mockito.any())).thenReturn(authorResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/author/" + authorResponse.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id")
                        .value(authorResponse.getId())
        );
    }

    @Test
    public void AuthorController_GetByValidName_ReturnSuccess_AndAuthorResponseData() throws Exception {
        Mockito.when(authorService.getByName(Mockito.any())).thenReturn(authorResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/author/name/" + authorResponse.getName())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name")
                        .value(authorResponse.getName())
        );
    }

    @Test
    public void AuthorController_GetByValidCountry_ReturnSuccess_AndListOfAuthorResponseData() throws Exception {
        Mockito.when(authorService.getAllByCountry(Mockito.any())).thenReturn(List.of(authorResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/author/country/" + authorResponse.getCountry())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id")
                        .value(authorResponse.getId())
        );
    }

    @Test
    public void AuthorController_Update_ReturnSuccess() throws Exception {
        Mockito.when(authorService.update(Mockito.any(), Mockito.any())).thenReturn(authorResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/author/" + authorResponse.getId())
                .content(objectMapper.writeValueAsString(authorRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}