package com.venturus.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    private HttpHeaders requestHeaders = new HttpHeaders();
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.requestHeaders.add("token", "c0f54b52-f181-4dd5-b464-e1df8feee9a1");
        /*this.mockMvc = MockMvcBuilders.standaloneSetup(UserController.class, UserBusiness.class, UserService.class).build();*/
    }

    @Test
    public void testGetAllUsersWithoutQueryParamsMustReturnAllUsersTokenCreated() throws Exception {
        /*mockMvc.perform(get("/users")
                .headers(requestHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));*/
    }

    @Test
    public void testGetAllUsersWithQueryParamsMustReturnAllUsersTokenCreatedRespectingQueryParams() throws Exception {
        /*UserDTO userDTO = new UserDTO();

        mockMvc.perform(
                post("/users")
                        .headers(requestHeaders)
                        .content(userDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(get("/users")
                .headers(requestHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));*/
    }

    @Test
    public void testGetUserByIdUsingTokenNotAllowedMustReturnForbidden() {

    }

    @Test
    public void testGetUserByIdUsingTokenAllowedMustReturnUser() {

    }

    @Test
    public void testPostUserMustReturnHeaderLocation() {

    }

    @Test
    public void testPutUserUsingTokenNotAllowedMustReturnForbidden() {

    }

    @Test
    public void testPutUserUsingTokenAllowedMustUpdateUser() {

    }

}
