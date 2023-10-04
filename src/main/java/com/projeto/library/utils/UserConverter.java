package com.projeto.library.utils;

import com.projeto.library.controller.dto.UserRequest;
import com.projeto.library.controller.dto.UserResponse;
import com.projeto.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static User toEntity(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public static UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());

        return userResponse;

    }

    public static List<UserResponse> toResponseList(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = UserConverter.toResponse(user);
            userResponses.add(userResponse);
        }
        return userResponses;
    }
}
