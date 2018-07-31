package com.example.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

    private MockRestServiceServer mockServer;
    private MovieService movieService;

    @Before()
    public void setup() {
        this.movieService = new MovieService();

        this.mockServer = MockRestServiceServer.createServer(movieService.getRestTemplate());
    }

    @Test
    public void testOmbdbCall() {
        mockServer.expect(requestTo("http://www.omdbapi.com/?s=test&apikey=e1e62e71"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"Search\": [{\"Title\": \"test\"}]}", MediaType.APPLICATION_JSON));

        assertThat(movieService.getMovies("test").get(0).getTitle(), equalTo("test"));

        mockServer.verify();
    }
}