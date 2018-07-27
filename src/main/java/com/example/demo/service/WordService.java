package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordService {

    public Map<String, Integer> wordCounter(String sentence) {
        Map<String, Integer> wordcount = new HashMap<>();

        String[] wordList = sentence.replaceAll("[^a-zA-Z ]", "").split(" ");

        for(String str: wordList){
            if(wordcount.containsKey(str)){
                Integer count = wordcount.get(str) + 1;
                wordcount.put(str, count);
            } else {
                wordcount.put(str, 1);
            }
        }


        return wordcount;
    }
}