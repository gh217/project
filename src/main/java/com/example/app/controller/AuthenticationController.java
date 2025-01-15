package com.example.app.controller;

import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Users;
import com.example.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @GetMapping("/login")
    public Object login(){
        return "login success";
    }

    @PostMapping("/singup1")
    public ResponseEntity<?> responseEntity  () {
        return new ResponseEntity<>(new SignUpRequestDto(),HttpStatus.ACCEPTED) ;
    }

    @PostMapping("/singup")
    public ResponseEntity<Users> register(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(authenticationService.sighUp(signUpRequestDto),HttpStatus.ACCEPTED) ;
    }


}
