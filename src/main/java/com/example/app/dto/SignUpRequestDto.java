package com.example.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {

    private String name;
    private String email;
    private String password;

    private int admin;
    private int user;
}
