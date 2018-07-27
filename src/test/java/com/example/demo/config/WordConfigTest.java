package com.example.demo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "wordcount.caseSensitive=false",
        "wordcount.words.skip[0]=the",
        "wordcount.words.skip[1]=an",
        "wordcount.words.skip[2]=a"
})
public class WordConfigTest {
    @Autowired
    private WordConfig config;

    @Test
    public void testPropertiesAreMappedCorrectly() {
        assertEquals(config.getCaseSensitive(), false);
        assertEquals(config.getWords().getSkip().get(0), "the");
        assertEquals(config.getWords().getSkip().get(1), "an");
        assertEquals(config.getWords().getSkip().get(2), "a");
    }
}