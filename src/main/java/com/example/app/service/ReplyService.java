package com.example.app.service;

import com.example.app.entity.Movie;
import com.example.app.entity.Reply;
import com.example.app.entity.Users;
import com.example.app.exception.NotFoundMovieExc;
import com.example.app.exception.NotFoundUserExc;
import com.example.app.repo.MovieRepository;
import com.example.app.repo.ReplyRepository;
import com.example.app.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final UsersService usersService;

    private final MovieService movieService;

    public String addReply(Long userId, Long movieId, String replyText) {
        Users user = usersService.userById(userId);
        Movie movie = movieService.movieById(movieId);

        Reply reply = new Reply();
        reply.setUsers(user);
        reply.setMovie(movie);
        reply.setReplyText(replyText);
        replyRepository.save(reply);
        return "Reply added successfully!";
    }

    public String deleteReply(Long movieId,Long userId) {
        Users users=usersService.userById(userId);
        Movie movie=movieService.movieById(movieId);

        replyRepository.deleteRateByUser(movie.getMovieId(), users.getId());
        return "Reply deleted successfully!";

    }
}
