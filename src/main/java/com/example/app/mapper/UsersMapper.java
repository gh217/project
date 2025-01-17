package com.example.app.mapper;

import com.example.app.dto.UsersDto;
import com.example.app.entity.Users;
import org.mapstruct.Mapper;

@Mapper()
public interface UsersMapper {
    UsersDto toUsersDto(Users users);
}
