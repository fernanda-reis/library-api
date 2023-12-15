package com.projeto.library.repository;

import com.projeto.library.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryUnitTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void setUp(){
        User user = new User(1, "João", "joao@email.com", "123456");
        userRepository.save(user);
    }

    @Test
    public void UserRepository_findByEmail_ReturnUsernameByEmail(){
        String email = "joao@email.com";

        UserDetails userDetails = userRepository.findByEmail(email);
        Assertions.assertEquals(userDetails.getUsername(), email);
    }

    @Test
    public void UserRepository_findAllByName_ReturnListOfUsersByName(){
        String name = "João";

        List<User> users = userRepository.findAllByName(name);
        Assertions.assertTrue(users.size() == 1);
        Assertions.assertEquals(users.get(0).getName(), name);

    }
}