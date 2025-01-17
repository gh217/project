package com.example.app.service;

import com.example.app.entity.Movie;
import com.example.app.entity.Rating;
import com.example.app.entity.Users;
import com.example.app.exception.NotFoundMovieExc;
import com.example.app.exception.NotFoundUserExc;
import com.example.app.repo.MovieRepository;
import com.example.app.repo.RatingRepository;
import com.example.app.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MovieRepository movieRepository;


    public ResponseEntity<?> addRating(Long userId, Long movieId, int ratingValue) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundUserExc("User not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundMovieExc("Movie not found"));

        if (ratingRepository.existsByUsersAndMovie(user, movie)) {
            return ResponseEntity.badRequest().body("You have already rated this movie!");
        }

        Rating rating = new Rating();
        rating.setUsers(user);
        rating.setMovie(movie);
        rating.setRating(ratingValue);
        ratingRepository.save(rating);
        return ResponseEntity.accepted().body("You have already rated this movie!");
    }

    public ResponseEntity<?> deleteRating(Long userId, Long movieId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundUserExc("User not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundMovieExc("Movie not found"));

        ratingRepository.deleteByUsersAndMovie(user, movie);
        return ResponseEntity.accepted().body("Rating deleted successfully!");
    }

}
