package com.example.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class WordServiceTest {

    private WordService wordService;

    @Before
    public void setup() {
        this.wordService = new WordService();
    }

    @Test
    public void testWordCount() {
        String str = "hello this is a test sentence test";

        Map<String, Integer> result = wordService.wordCounter(str);

        assertEquals(result.get("hello").intValue(), 1);
        assertEquals(result.get("this").intValue(), 1);
        assertEquals(result.get("is").intValue(), 1);
        assertEquals(result.get("a").intValue(), 1);
        assertEquals(result.get("sentence").intValue(), 1);
        assertEquals(result.get("test").intValue(), 2);
    }

    @Test
    public void testWordCount_removePunctuation() {
        String str = "hello this, is a test, sentence test";

        Map<String, Integer> result = wordService.wordCounter(str);

        assertEquals(result.get("hello").intValue(), 1);
        assertEquals(result.get("this").intValue(), 1);
        assertEquals(result.get("is").intValue(), 1);
        assertEquals(result.get("a").intValue(), 1);
        assertEquals(result.get("sentence").intValue(), 1);
        assertEquals(result.get("test").intValue(), 2);
    }


}