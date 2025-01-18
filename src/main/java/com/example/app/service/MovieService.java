package com.example.app.service;

import com.example.app.dto.MovieDto;
import com.example.app.entity.Movie;
import com.example.app.mapper.MoviesMapper;
import com.example.app.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MoviesMapper moviesMapper;


    public MovieDto createMovie(String title) {
        Movie movie = new Movie();
        movie.setTitle(title);
        return moviesMapper.tomovieDto(movieRepository.save(movie));
    }


    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(moviesMapper::tomovieDto)
                .toList();
    }


    public MovieDto getMovie(Long movieId) {

        Movie movie= movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        return moviesMapper.tomovieDto(movie);
    }


}
