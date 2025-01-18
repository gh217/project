package com.example.app.controller;


import com.example.app.dto.MovieDto;
import com.example.app.entity.Movie;
import com.example.app.entity.Users;
import com.example.app.service.MovieService;
import com.example.app.service.RatingService;
import com.example.app.service.ReplyService;
import com.example.app.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {


    private final MovieService movieService;
    private final RatingService ratingService;
    private final ReplyService replyService;

    @PostMapping("/movies")
    public MovieDto createMovie(@RequestParam String title) {
        return movieService.createMovie(title);
    }

    @PostMapping("/movies/{movieId}/rate/{userId}")
    public String addRating(@PathVariable Long movieId, @PathVariable Long userId, @RequestParam int rating) {
        return ratingService.addRating(userId, movieId, rating);
    }


    @DeleteMapping("/movies/{movieId}/rate/{userId}")
    public String deleteRating(@PathVariable Long movieId, @PathVariable Long userId) {
        log.warn("yes you are here");
        return ratingService.deleteRating(movieId,userId);
    }

    
    @PostMapping("/movies/{movieId}/reply/{userId}")
    public String addReply(@PathVariable Long movieId, @PathVariable Long userId, @RequestParam String replyText) {
        return replyService.addReply(userId, movieId, replyText);
    }


    @DeleteMapping("/movies/{movieId}/reply/{userId}")
    public String deleteReply(@PathVariable Long movieId, @PathVariable Long userId) {
        return replyService.deleteReply(userId, movieId);
    }


}
