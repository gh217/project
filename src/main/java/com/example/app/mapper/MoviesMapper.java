package com.example.app.mapper;

import com.example.app.dto.MovieDto;
import com.example.app.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviesMapper {
    MovieDto tomovieDto(Movie movie);
}
