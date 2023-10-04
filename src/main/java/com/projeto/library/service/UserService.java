package com.projeto.library.service;

import com.projeto.library.controller.dto.UserRequest;
import com.projeto.library.controller.dto.UserResponse;
import com.projeto.library.model.User;
import com.projeto.library.repository.UserRepository;
import com.projeto.library.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    UserRepository repository;
    public UserResponse getById(Integer id) {
        Optional<User> userResponse =  repository.findById(id);
        if(userResponse.isPresent()){
            return UserConverter.toResponse(userResponse.get());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public List<UserResponse> getAll() {
        return UserConverter.toResponseList(repository.findAll());
    }

    public List<UserResponse> getAllByName(String name){
        return UserConverter.toResponseList(repository.findAllByName(name));
    }

    public UserResponse save(UserRequest userRequest) {
        User user = UserConverter.toEntity(userRequest);
        return UserConverter.toResponse(repository.save(user));
    }

    public UserResponse getByEmail(String email) {
        return UserConverter.toResponse(repository.findByEmail(email).get());

    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public UserResponse update(Integer id, UserRequest userRequest) {
        User user = UserConverter.toEntity(userRequest);
        user.setId(id);
        return UserConverter.toResponse(repository.save(user));
    }
}
