package com.example.app.repo;

import com.example.app.entity.Movie;
import com.example.app.entity.Rating;
import com.example.app.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    boolean existsByUsersAndMovie(Users user, Movie movie); // Check if user already rated the movie prevent duplicate

    @Transactional
    @Modifying
    @Query(value = "delete from ratings where movie_id=:movieId and user_id=:userId" , nativeQuery = true)
    void deleteRateByUser(Long movieId , Long userId); // Delete rating by user and movie

}
