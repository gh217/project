package com.example.app.service;

import com.example.app.dto.SignUpDto;
import com.example.app.entity.Users;

public interface AuthenticationService {
    public Users sighUp(SignUpDto signUpDto);
}
