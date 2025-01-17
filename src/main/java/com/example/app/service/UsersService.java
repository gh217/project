package com.example.app.service;

import com.example.app.dto.UsersDto;
import com.example.app.entity.Users;
import com.example.app.exception.NotFoundUserExc;
import com.example.app.mapper.UsersMapper;
import com.example.app.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersMapper usersMapper;

    @Autowired
    private UsersRepository usersRepository;


    @Cacheable("users")
    public List<UsersDto> getAllUsers() {
        return usersRepository
                .findAll()
                .stream()
                .map(usersMapper::toUsersDto)
                .toList();
    }

    @Cacheable(value = "user",key = "#userId")
    public UsersDto getUser(Long userId) {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new NotFoundUserExc("User not found"));
        return usersMapper.toUsersDto(users);
    }


}
