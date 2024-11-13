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

import com.administrator.filmarte.controller.CategoryController;
import com.administrator.filmarte.model.Category;
import com.administrator.filmarte.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setIdCategory(1);
        category.setCategoryType("Entertainment");
        category.setDescription("Includes movies, music, and games.");
    }

    @Test
    void testGetAll() throws Exception {
        List<Category> categories = Arrays.asList(category);
        when(categoryService.getAll()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryType").value(category.getCategoryType()));
    }

    @Test
    void testGetById() throws Exception {
        when(categoryService.getById(1)).thenReturn(category);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryType").value(category.getCategoryType()));
    }

    @Test
    void testRegister() throws Exception {
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(content().string("Saved record"));
    }

    @Test
    void testUpdate() throws Exception {
        when(categoryService.getById(1)).thenReturn(category);

        mockMvc.perform(put("/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated record"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(categoryService).delete(1);
        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isNoContent());
    }

}
