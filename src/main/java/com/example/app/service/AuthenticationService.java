package com.example.app.service;

import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Users;

public interface AuthenticationService {
    public Users sighUp(SignUpRequestDto signUpRequestDto);
}
