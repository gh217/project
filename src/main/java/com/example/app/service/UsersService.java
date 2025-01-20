package com.example.app.service;

import com.example.app.dto.UsersDto;
import com.example.app.entity.Users;
import com.example.app.exception.NotFoundUserExc;
import com.example.app.mapper.UsersMapper;
import com.example.app.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersMapper usersMapper;

    private final UsersRepository usersRepository;

    public Page<UsersDto>getUsers(int page , int size ){
        Page <Users> pageable= usersRepository.findAll(PageRequest.of(page,size));
        return pageable.map(usersMapper::toUsersDto);
    }


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

    @Cacheable(value = "user",key = "#userId")
    public Users userById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new NotFoundUserExc("User not found"));
    }

}
