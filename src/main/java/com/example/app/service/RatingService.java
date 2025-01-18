package com.example.app.service;

import com.example.app.entity.Movie;
import com.example.app.entity.Rating;
import com.example.app.entity.Users;
import com.example.app.repo.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UsersService usersService;
    private final MovieService movieService ;


    public String addRating(Long userId, Long movieId, int ratingValue) {
        Users user = usersService.userById(userId);
        Movie movie = movieService.movieById(movieId);

        if (ratingRepository.existsByUsersAndMovie(user, movie)) {
            return "You have already rated this movie!";
        }

        Rating rating = new Rating();
        rating.setUsers(user);
        rating.setMovie(movie);
        rating.setRating(ratingValue);
        ratingRepository.save(rating);
        return "You have already rated this movie!";
    }

    public String deleteRating(Long userId, Long movieId) {
        Users user = usersService.userById(userId);
        Movie movie = movieService.movieById(movieId);

        ratingRepository.deleteRateByUser(movie.getMovieId(), user.getId());
        return "Rating deleted successfully!";
    }

}
