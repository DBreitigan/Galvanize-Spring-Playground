package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private String title;
    private String imdbId;
    private String poster;
    private int year;

    @JsonProperty(value = "title")
    public String getTitle() {
        return title;
    }

    @JsonProperty(value = "Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty(value = "imdbId")
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty(value = "imdbID")
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @JsonProperty(value = "poster")
    public String getPoster() {
        return poster;
    }

    @JsonProperty(value = "Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @JsonProperty(value = "year")
    public int getYear() {
        return year;
    }

    @JsonProperty(value = "Year")
    public void setYear(int year) {
        this.year = year;
    }
}
