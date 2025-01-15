package com.example.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {

    private String name;
    private String email;
    private String password;

    private int isAdmin=0;
    private int isUser=0;
}
