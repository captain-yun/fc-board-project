package com.kitri.boardproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainControllerTest.class)
@Import(SecurityConfig.class)
class MainControllerTest {
    private final MockMvc mvc;

    public MainControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void givenRootNothing_whenRequestingRootPage_thenRedirectsToArticlesPage() throws Exception {
        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }
}