package com.projeto.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.library.controller.dto.UserRequest;
import com.projeto.library.controller.dto.UserResponse;
import com.projeto.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerIntegrationTest {

    UserRequest userRequest;
    UserResponse userResponse;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        userRequest = new UserRequest("Maria", "maria@email.com", "123456");

        userResponse = new UserResponse();
        userResponse.setId(1);
        userResponse.setName(userRequest.getName());
        userResponse.setEmail(userRequest.getEmail());
    }

    @Test
    public void UserController_GetByValidId_ReturnSuccess_AndUserResponseData() throws Exception {
        Mockito.when(userService.getById(Mockito.any())).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userResponse.getId())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name")
                        .value("Maria")
        );
    }

    @Test
    public void UserController_GetAll_ReturnSuccess_AndListOfUserResponseData() throws Exception {
        Mockito.when(userService.getAll()).thenReturn(List.of(userResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name")
                        .value("Maria")
        );
    }

        @Test
    public void UserController_SaveValidUser_ReturnSuccess() throws Exception {
        Mockito.when(userService.save(Mockito.any())).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(userRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void UserController_GetAllByValidName_ReturnSuccess_AndListOfUserResponseData() throws Exception {
        Mockito.when(userService.getAllByName(Mockito.any())).thenReturn(List.of(userResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/name/" + userResponse.getName())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name")
                        .value("Maria")
        );
    }

    @Test
    public void UserController_Update_ReturnSuccess() throws Exception {
        Mockito.when(userService.update(Mockito.any(), Mockito.any())).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/" + userResponse.getId())
                .content(objectMapper.writeValueAsString(userRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


}