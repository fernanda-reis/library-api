package com.projeto.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.library.controller.dto.CategoryRequest;
import com.projeto.library.controller.dto.CategoryResponse;
import com.projeto.library.service.CategoryService;
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
class CategoryControllerIntegrationTest {

    CategoryRequest categoryRequest;
    CategoryResponse categoryResponse;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        categoryRequest = new CategoryRequest();
        categoryRequest.setName("unit-test");

        categoryResponse = new CategoryResponse();
        categoryResponse.setId(1);
        categoryResponse.setName(categoryRequest.getName());
    }

    @Test
    public void CategoryController_SaveValidCategory_ReturnSuccess() throws Exception {
        Mockito.when(categoryService.save(Mockito.any())).thenReturn(categoryResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .content(objectMapper.writeValueAsString(categoryRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void CategoryController_GetAll_ReturnSuccess_AndListOfCategoryResponseData() throws Exception {
        Mockito.when(categoryService.getAll()).thenReturn(List.of(categoryResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/category")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id")
                        .value(categoryResponse.getId())
        );
    }

    @Test
    public void CategoryController_GetByValidId_ReturnSuccess_AndCategoryResponseData() throws Exception {
        Mockito.when(categoryService.getById(Mockito.any())).thenReturn(categoryResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/category/" + categoryResponse.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id")
                        .value(categoryResponse.getId())
        );
    }

    @Test
    public void CategoryController_GetByValidName_ReturnSuccess_AndCategoryResponseData() throws Exception {
        Mockito.when(categoryService.getByName(Mockito.any())).thenReturn(categoryResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/category/name/" + categoryResponse.getName())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name")
                        .value(categoryResponse.getName())
        );

    }

    @Test
    public void CategoryController_Update_ReturnSuccess() throws Exception {
        Mockito.when(categoryService.update(Mockito.any(), Mockito.any())).thenReturn(categoryResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/category/" + categoryResponse.getId())
                .content(objectMapper.writeValueAsString(categoryRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}