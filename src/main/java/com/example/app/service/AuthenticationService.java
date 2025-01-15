package com.example.app.service;

import com.example.app.dto.JwtAuthenticationResponseDto;
import com.example.app.dto.SignInRequestDto;
import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Users;

public interface AuthenticationService {
    public Users sighUp(SignUpRequestDto signUpRequestDto);
    public JwtAuthenticationResponseDto singin(SignInRequestDto signInRequestDto);
}
