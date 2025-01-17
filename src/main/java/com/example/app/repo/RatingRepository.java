package com.example.app.repo;

import com.example.app.entity.Movie;
import com.example.app.entity.Rating;
import com.example.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    boolean existsByUsersAndMovie(Users user, Movie movie); // Check if user already rated the movie

}
