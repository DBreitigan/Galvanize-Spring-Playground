package com.example.demo.service;

import com.example.demo.config.WordConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class WordServiceTest {

    private WordService wordService;

    private WordConfig config;


    @Before
    public void setup() {
        this.config = new WordConfig();
        config.setCaseSensitive(false);
        WordConfig.Words words=  new WordConfig.Words();
        words.setSkip(asList());
        config.setWords(words);
        this.wordService = new WordService(this.config);
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