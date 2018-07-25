package com.example.demo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
public class MathServiceTest {

    private MathService mathService;

    @Before
    public void setup() {
        mathService = new MathService();
    }

    @Test
    public void testCalculateAddition() {
        String result = mathService.calculate("add", 1 , 2);

        assertEquals(result, "1 + 2 = 3");
    }

    @Test
    public void testCalculateMultiply() {
        String result = mathService.calculate("multiply", 2 , 2);

        assertEquals(result, "2 * 2 = 4");
    }

    @Test
    public void testCalculateSubtract() {
        String result = mathService.calculate("subtract", 5 , 2);

        assertEquals(result, "5 - 2 = 3");
    }

    @Test
    public void testCalculateDivide() {
        String result = mathService.calculate("divide", 10 , 2);

        assertEquals(result, "10 / 2 = 5");
    }

    @Test
    public void testSum() {
        List<Integer> intList = new ArrayList<>();
        intList.add(5);
        intList.add(10);
        intList.add(34);
        String result = mathService.sum(intList);

        assertEquals(result, "5 + 10 + 34 = 49");
    }
}
