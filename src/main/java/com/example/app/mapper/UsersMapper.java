package com.example.app.mapper;

import com.example.app.dto.UsersDto;
import com.example.app.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto toUsersDto(Users users);
}
