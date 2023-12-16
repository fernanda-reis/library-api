package com.projeto.library.repository;

import com.projeto.library.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryIntegrationTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    public void setUp(){
        Category category = new Category(1, "Fiction");
        categoryRepository.save(category);
    }

    @Test
    public void CategoryRepository_findByName_ReturnCategoryByName(){
        String name = "Fiction";

        Category category = categoryRepository.findByName(name).get();
        Assertions.assertEquals(category.getName(), name);
    }
}