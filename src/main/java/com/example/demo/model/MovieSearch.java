package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieSearch {
    private List<Movie> search;

    public List<Movie> getSearch() {
        return search;
    }

    @JsonProperty(value = "Search")
    public void setSearch(List<Movie> search) {
        this.search = search;
    }
}
