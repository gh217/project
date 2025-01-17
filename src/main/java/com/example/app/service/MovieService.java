package com.example.app.service;

import com.example.app.entity.Movie;
import com.example.app.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(String title) {
        Movie movie = new Movie();
        movie.setTitle(title);
        return movieRepository.save(movie);
    }

    @Cacheable("movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Cacheable(value = "movie",key = "#movieId")
    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
    }



}
