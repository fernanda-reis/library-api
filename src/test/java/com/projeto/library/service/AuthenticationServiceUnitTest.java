package com.projeto.library.service;

import com.projeto.library.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
class AuthenticationServiceUnitTest {
    UserDetails userDetails;

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }

    @Test
    public void AuthenticationService_LoadByValidUserName_WithSuccess(){
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(userDetails);
        UserDetails newUserDetails = authenticationService.loadUserByUsername("unit-test");
        Assertions.assertNotNull(newUserDetails);
    }

    @Test
    public void AuthenticationService_LoadByInvalidUserName_WithFailure(){
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> authenticationService.loadUserByUsername("unit-test"));
    }


}