package com.projeto.library.utils;

import com.projeto.library.controller.dto.CategoryRequest;
import com.projeto.library.controller.dto.CategoryResponse;
import com.projeto.library.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter {
    public static Category toEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return category;
    }

    public static CategoryResponse toResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    public static List<CategoryResponse> ToResponseList(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for(Category category : categories) {
            categoryResponses.add(toResponse(category));
        }
        return categoryResponses;
    }

}
