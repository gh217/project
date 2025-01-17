package com.example.app.service;

import com.example.app.entity.Movie;
import com.example.app.entity.Reply;
import com.example.app.entity.Users;
import com.example.app.exception.NotFoundMovieExc;
import com.example.app.exception.NotFoundUserExc;
import com.example.app.repo.MovieRepository;
import com.example.app.repo.ReplyRepository;
import com.example.app.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<?> addReply(Long userId, Long movieId, String replyText) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundUserExc("User not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundMovieExc("Movie not found"));

        Reply reply = new Reply();
        reply.setUsers(user);
        reply.setMovie(movie);
        reply.setReplyText(replyText);
        replyRepository.save(reply);
        return ResponseEntity.accepted().body("Reply added successfully!");
    }

    public ResponseEntity<?> deleteReply(Long userId, Long movieId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundUserExc("User not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundMovieExc("Movie not found"));

        replyRepository.deleteByUsersAndMovie(user, movie);
        return ResponseEntity.accepted().body("Reply deleted successfully!");

    }
}
