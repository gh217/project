package com.example.app.controller;


import com.example.app.dto.UsersDto;
import com.example.app.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @GetMapping("/{id}")
    public UsersDto getUser(@PathVariable Long id) {
        return usersService.getUser(id);
    }

}
