package com.projeto.library.service;

import com.projeto.library.controller.dto.UserRequest;
import com.projeto.library.controller.dto.UserResponse;
import com.projeto.library.model.User;
import com.projeto.library.repository.UserRepository;
import com.projeto.library.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserResponse getById(Integer id) {
        Optional<User> userResponse =  repository.findById(id);
        if(userResponse.isPresent()){
            return UserConverter.toResponse(userResponse.get());
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    public List<UserResponse> getAll() {
        return UserConverter.toResponseList(repository.findAll());
    }

    public List<UserResponse> getAllByName(String name){
        List<User> users;
        users = repository.findAllByName(name);

        if(users.isEmpty()) {
            throw new RuntimeException("No users found.");
        }
        return UserConverter.toResponseList(users);
    }

    public UserResponse save(UserRequest userRequest) {
        User user = UserConverter.toEntity(userRequest);

        if (!userRequest.getEmail().contains("@") ) {
            throw new RuntimeException("Invalid email format.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return UserConverter.toResponse(repository.save(user));
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
