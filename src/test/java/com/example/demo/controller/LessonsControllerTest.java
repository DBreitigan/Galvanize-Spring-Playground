package com.example.demo.controller;

import com.example.demo.database.EmployeeRepository;
import com.example.demo.database.LessonRepository;
import com.example.demo.model.Lesson;
import com.example.demo.service.EmployeeDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
public class LessonsControllerTest {
    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    EmployeeDetailsService employeeDetailsService;

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testGet() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("super sweet title");

        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"some title\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByTitle() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("some really weird unique title4");

        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons/find/{title}", lesson.getTitle());

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", is(lesson.getTitle())));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("super sweet title");

        repository.save(lesson);

        long id = lesson.getId();

        MockHttpServletRequestBuilder request = patch("/lessons/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"another super sweet title\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", is("another super sweet title")));
    }

    @Test
    @Transactional
    @Rollback
    public void testbetween() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("super sweet title");
        lesson.setDeliveredOn(new Date());

        repository.save(lesson);

        Date date1 = new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" );
        Date date2 = new SimpleDateFormat( "yyyyMMdd" ).parse( "20200520" );

        MockHttpServletRequestBuilder request = get("/lessons/between?date1={date1}&date2={date2}", date1, date2)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class)))
                .andExpect(jsonPath("$[0].title", instanceOf(String.class)));
    }


}
