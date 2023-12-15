package com.projeto.library.service;

import com.projeto.library.controller.dto.CategoryRequest;
import com.projeto.library.controller.dto.CategoryResponse;
import com.projeto.library.model.Category;
import com.projeto.library.repository.CategoryRepository;
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
class CategoryServiceUnitTest {
    CategoryRequest categoryRequest;
    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        categoryRequest = new CategoryRequest();
        categoryRequest.setName("unit-test");
    }

    @Test
    public void CategoryService_SaveValidCategory_ReturnCategoryResponse(){
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(new Category());
        CategoryResponse categoryResponse = categoryService.save(categoryRequest);
        Assertions.assertNotNull(categoryResponse);
    }

    @Test
    public void CategoryService_GetAll_ReturnListOfCategoryResponse(){
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(new Category()));
        List<CategoryResponse> categories = categoryService.getAll();
        Assertions.assertNotNull(categories);
        Assertions.assertFalse(categories.isEmpty());
    }

    @Test
    public void CategoryService_GetByValidId_ReturnCategoryResponse(){
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(new Category()));
        CategoryResponse categoryResponse = categoryService.getById(1);
        Assertions.assertNotNull(categoryResponse);
    }

    @Test
    public void CategoryService_GetByValidName_ReturnCategoryResponse(){
        Mockito.when(categoryRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Category()));
        CategoryResponse categoryResponse = categoryService.getByName("unit-test");
        Assertions.assertNotNull(categoryResponse);
    }

    @Test
    public void CategoryService_Update_ReturnCategory(){
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(new Category());
        CategoryResponse category = categoryService.update(1, categoryRequest);
        Assertions.assertNotNull(category);
    }

    @Test
    public void CategoryService_Detele_WithSuccess(){
        categoryService.delete(1);
    }

}