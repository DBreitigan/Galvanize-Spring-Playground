package com.example.demo.controller;

import com.example.demo.database.EmployeeRepository;
import com.example.demo.security.SecurityConfig;
import com.example.demo.service.EmployeeDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
@Import(SecurityConfig.class)
public class EmployeesControllerTest {
    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    EmployeeDetailsService employeeDetailsService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testWithUser() throws Exception {
        RequestBuilder request = get("/employees").with(user("user").roles("EMPLOYEE"));
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void AdminEmployeesWithUser() throws Exception {
        RequestBuilder request = get("/admin/employees").with(user("user").roles("EMPLOYEE"));
        mvc.perform(request).andExpect(status().isForbidden());
    }

    @Test
    public void AdminEmployeesWithNoUser() throws Exception {
        RequestBuilder request = get("/admin/employees");
        mvc.perform(request).andExpect(status().isUnauthorized());
    }

    @Test
    public void AdminEmployeesWithAdmin() throws Exception {
        RequestBuilder request = get("/admin/employees").with(user("user").roles("MANAGER"));
        mvc.perform(request).andExpect(status().isOk());
    }

}
