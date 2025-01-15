package com.example.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpDto {

    private String name;
    private String email;
    private String password;

    private boolean isAdmin=false;
    private boolean isUser=false;
}
