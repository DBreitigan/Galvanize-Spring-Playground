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
        Map<String, Integer> wordcount = new HashMap<>();

        String[] wordList = sentence.replaceAll("[^a-zA-Z ]", "").split(" ");

        for(String str: wordList){
            if(!config.getWords().getSkip().contains(str)) {
                if(config.getCaseSensitive()){
                    if(wordcount.containsKey(str)){
                        Integer count = wordcount.get(str) + 1;
                        wordcount.put(str, count);
                    } else {
                        wordcount.put(str, 1);
                    }
                } else {
                    if(wordcount.containsKey(str.toLowerCase())){
                        Integer count = wordcount.get(str.toLowerCase()) + 1;
                        wordcount.put(str.toLowerCase(), count);
                    } else {
                        wordcount.put(str.toLowerCase(), 1);
                    }
                }

            }
        }


        return wordcount;
    }
}