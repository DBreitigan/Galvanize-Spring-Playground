package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.model.MovieSearch;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {

    private final RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public List<Movie> getMovies(String query) {
        MovieSearch responseEntity = this.restTemplate.getForObject("http://www.omdbapi.com/?s={query}&apikey={apiKey}", MovieSearch.class, query, "e1e62e71");
        return responseEntity != null ? responseEntity.getSearch() : null;
    }
}
