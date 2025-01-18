package com.example.app.controller;


import com.example.app.dto.MovieDto;
import com.example.app.entity.Movie;
import com.example.app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public MovieDto createMovie(@RequestParam String title) {
        return movieService.createMovie(title);
    }

}
