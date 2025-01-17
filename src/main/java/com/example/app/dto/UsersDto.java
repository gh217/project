package com.example.app.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UsersDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

}
