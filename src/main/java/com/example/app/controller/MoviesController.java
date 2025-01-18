package com.example.app.controller;


import com.example.app.dto.MovieDto;
import com.example.app.entity.Movie;
import com.example.app.mapper.MoviesMapper;
import com.example.app.service.MovieService;
import com.example.app.service.RatingService;
import com.example.app.service.ReplyService;
import com.example.app.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MovieService movieService;


    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }


}
