package com.example.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInRequestDto {

    private String email;
    private String password;
}
