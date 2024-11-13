package com.administrator.filmarte.Controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.administrator.filmarte.controller.AdministratorController;
import com.administrator.filmarte.model.Administrator;
import com.administrator.filmarte.service.AdministratorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdministratorController.class)
public class AdministratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdministratorService administratorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Administrator administrator;

    @BeforeEach
    void setUp() {
        administrator = new Administrator(0, null, null, null, null, null);
        administrator.setIdAdministrator(1);
        administrator.setName("John");
        administrator.setLastname("Doe");
        administrator.setEmail("john.doe@example.com");
        administrator.setPassword("password123");
    }

    @Test
    void testGetAll() throws Exception {
        List<Administrator> administrators = Arrays.asList(administrator);
        when(administratorService.getAll()).thenReturn(administrators);

        mockMvc.perform(get("/administrators"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(administrator.getName()));
    }

    @Test
    void testGetByIdAdministrator() throws Exception {
        when(administratorService.getByIAdministrator(1)).thenReturn(administrator);

        mockMvc.perform(get("/administrators/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(administrator.getName()));
    }

    @Test
    void testRegister() throws Exception {
        mockMvc.perform(post("/administrators")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrator)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        when(administratorService.getByIAdministrator(1)).thenReturn(administrator);

        mockMvc.perform(put("/administrators/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrator)))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated record"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(administratorService).delete(1);

        mockMvc.perform(delete("/administrators/1"))
                .andExpect(status().isNoContent());
    }
}
