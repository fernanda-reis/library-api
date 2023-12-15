package com.projeto.library.service;

import com.projeto.library.controller.dto.UserRequest;
import com.projeto.library.controller.dto.UserResponse;
import com.projeto.library.model.User;
import com.projeto.library.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;



@ExtendWith(SpringExtension.class)
class UserServiceUnitTest {
    UserRequest userRequest;

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){
        userRequest = new UserRequest("maria", "maria@hotmail.com", "123456");
    }

    @Test
    public void UserService_GetByValidId_ReturnUserResponse(){
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new User()));
        UserResponse userResponse = userService.getById(1);
        Assertions.assertNotNull(userResponse);
    }

    @Test
    public void UserService_GetByInvalidId_ThrowException() {
        Assertions.assertThrows(RuntimeException.class,
                () -> userService.getById(1));
    }

    @Test
    public void UserService_GetAll_ReturnListOfUserResponse(){
        Mockito.when(userRepository.findAll()).thenReturn(List.of(new User()));
        List<UserResponse> users = userService.getAll();
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    public void UserService_GetAllByValidName_ReturnListOfUserResponse(){
        Mockito.when(userRepository.findAllByName(Mockito.any())).thenReturn(List.of(new User()));
        List<UserResponse> users = userService.getAllByName("unit-test");
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    public void UserService_GetAllByNonRegisteredName_ThrowsException(){
        Assertions.assertThrows(RuntimeException.class,
                () -> userService.getAllByName("unit-test"));
    }

    @Test
    public void UserService_SaveValidUser_ReturnUserResponse(){
        Mockito.when(passwordEncoder.encode(Mockito.any())).thenReturn("unit-test");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());
        UserResponse userResponse = userService.save(userRequest);
        Assertions.assertNotNull(userResponse);
    }

    @Test
    public void UserService_SaveUserWithInvalidEmail_ThrowsException(){
        Assertions.assertThrows(RuntimeException.class,
                () -> userService.save(new UserRequest("test", "test", "123456")));
    }

    @Test
    public void UserService_Delete_WithSuccess(){
        userService.delete(1);
    }

    @Test
    public void UserService_Update_ReturnUserResponse(){
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());
        UserResponse user = userService.update(1, userRequest);
        Assertions.assertNotNull(user);
    }


}