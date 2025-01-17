package com.example.app.repo;

import com.example.app.entity.Movie;
import com.example.app.entity.Reply;
import com.example.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository  extends JpaRepository<Reply, Long> {
    void deleteByUsersAndMovie(Users users, Movie movie); // Delete replies by user and movie
}
