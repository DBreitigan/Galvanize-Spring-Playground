package com.example.demo.controller;

import com.example.demo.database.EmployeeRepository;
import com.example.demo.service.EmployeeDetailsService;
import com.example.demo.service.WordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
@AutoConfigureMockMvc(secure=false)
public class WordControllerTest {
    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    EmployeeDetailsService employeeDetailsService;

    @Autowired
    MockMvc mvc;

    @MockBean
    private WordService service;

    @Test
    public void testWordCount() throws Exception {
        String request = "this is a test string";

        Map<String, Integer> map = new HashMap<>();
        map.put("this", 1);
        map.put("is", 1);
        map.put("a", 1);
        map.put("test", 1);
        map.put("string", 1);

        when(service.wordCounter(request)).thenReturn(map);

        this.mvc.perform(post("/word/count")
                .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.this", is(1)))
                .andExpect(jsonPath("$.is", is(1)))
                .andExpect(jsonPath("$.a", is(1)))
                .andExpect(jsonPath("$.test", is(1)))
                .andExpect(jsonPath("$.string", is(1)));
    }

}