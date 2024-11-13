package com.administrator.filmarte.Controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import com.administrator.filmarte.controller.ActorController;
import com.administrator.filmarte.model.Actor;
import com.administrator.filmarte.service.ActorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ActorController.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService actorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Actor actor;

    @BeforeEach
    void setUp() {
        actor = new Actor(null, null, 0, null);//modify
        actor.setIdActor(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        actor.setDescription("An experienced actor in theater and film.");
    }

    @Test
    void testGetAll() throws Exception {
        List<Actor> actors = Arrays.asList(actor);
        when(actorService.getAll()).thenReturn(actors);

        mockMvc.perform(get("/actors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(actor.getFirstName()));
    }

    @Test
    void testGetById() throws Exception {
        when(actorService.getById(1)).thenReturn(actor);

        mockMvc.perform(get("/actors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(actor.getFirstName()));
    }

    @Test
    void testRegister() throws Exception {
        mockMvc.perform(post("/actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actor)))
                .andExpect(status().isOk())
                .andExpect(content().string("Saved record"));
    }

    @Test
    void testUpdate() throws Exception {
        when(actorService.getById(1)).thenReturn(actor);

        mockMvc.perform(put("/actors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actor)))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated record"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/actors/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted record"));
    }
}
