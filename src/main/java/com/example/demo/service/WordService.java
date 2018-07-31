package com.example.demo.service;

import com.example.demo.config.WordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordService {

    private WordConfig config;

    @Autowired
    public WordService(WordConfig config) {
        this.config = config;
    }

    public Map<String, Integer> wordCounter(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] wordList = sentence.replaceAll("[^a-zA-Z ]", "").split(" ");

        for(String str: wordList){
            if(!config.getWords().getSkip().contains(str)) {
                if(config.getCaseSensitive()){
                    if(wordCount.containsKey(str)){
                        Integer count = wordCount.get(str) + 1;
                        wordCount.put(str, count);
                    } else {
                        wordCount.put(str, 1);
                    }
                } else {
                    if(wordCount.containsKey(str.toLowerCase())){
                        Integer count = wordCount.get(str.toLowerCase()) + 1;
                        wordCount.put(str.toLowerCase(), count);
                    } else {
                        wordCount.put(str.toLowerCase(), 1);
                    }
                }
            }
        }

        return wordCount;
    }
}