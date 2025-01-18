package com.example.app.repo;

import com.example.app.entity.Movie;
import com.example.app.entity.Reply;
import com.example.app.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository  extends JpaRepository<Reply, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from replies where movie_id=:movieId and user_id=:userId", nativeQuery = true)
    void deleteRateByUser(Long movieId,Long userId); // Delete replies by user and movie

}
