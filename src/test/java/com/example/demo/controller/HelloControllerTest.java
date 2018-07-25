package com.example.demo.controller;

import com.example.demo.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MathService mathService;

    @Test
    public void testHelloWorldEndpoint_works() throws Exception {
        RequestBuilder request = get("/");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Spring!"));
    }

    @Test
    public void testMathPiEndpoint_works() throws Exception {
        RequestBuilder request = get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testMathCalculateEndpoint_Works() throws Exception {
        when(mathService.calculate("add", 4, 6)).thenReturn("4 + 6 = 10");
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testMathCalculateEndpoint_HasOptionalOperationParameter() throws Exception {
        when(mathService.calculate("add", 4, 6)).thenReturn("4 + 6 = 10");
        this.mvc.perform(get("/math/calculate?&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testMathSumEndpoint_Works() throws Exception {
        List<Integer> intList = new ArrayList<>();
        intList.add(5);
        intList.add(10);
        intList.add(34);

        when(mathService.sum(intList)).thenReturn("5 + 10 + 34 = 49");
        this.mvc.perform(post("/math/sum?n=5&n=10&n=34"))
                .andExpect(status().isOk())
                .andExpect(content().string("5 + 10 + 34 = 49"));
    }

}
