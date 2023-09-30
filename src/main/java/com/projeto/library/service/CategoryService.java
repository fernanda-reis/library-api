package com.projeto.library.service;

import com.projeto.library.controller.dto.CategoryRequest;
import com.projeto.library.controller.dto.CategoryResponse;
import com.projeto.library.model.Category;
import com.projeto.library.repository.CategoryRepository;
import com.projeto.library.utils.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public CategoryResponse save(CategoryRequest categoryRequest){
        Category category = CategoryConverter.toEntity(categoryRequest);
        return CategoryConverter.toResponse(repository.save(category));
    }

    public List<CategoryResponse> getAll() {
        return CategoryConverter.ToResponseList(repository.findAll());
    }

    public CategoryResponse getById(Integer id){
        return CategoryConverter.toResponse(repository.findById(id).get());
    }

    public CategoryResponse getByName(String name) {
        return CategoryConverter.toResponse((repository.findByName(name).get()));
    }

    public CategoryResponse update(Integer id, CategoryRequest categoryRequest) {
        Category category = CategoryConverter.toEntity(categoryRequest);
        category.setId(id);
        return CategoryConverter.toResponse(repository.save(category));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
