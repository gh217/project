package com.example.app.controller;

import com.example.app.dto.JwtAuthenticationResponseDto;
import com.example.app.dto.SignInRequestDto;
import com.example.app.repo.UsersRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Users;
import com.example.app.security.AuthenticationService;
import com.example.app.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UsersRepository usersRepository;


    @PostMapping("/singup")
    public ResponseEntity<Users> register(@RequestBody SignUpRequestDto signUpRequestDto) {
        Users users=authenticationService.sighUp(signUpRequestDto);
        log.info("user registered successfully  "+signUpRequestDto.getAdmin());
        return  ResponseEntity.ok().body(users);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponseDto> login(@RequestBody SignInRequestDto signInRequestDto) {
         return ResponseEntity.ok(authenticationService.singin(signInRequestDto));
    }


    @PostMapping("/checkToken")
    public Boolean checkToken(@Param("token") String token , @Param("email") String email) {
        return jwtService.isTokenValid(token, usersRepository.findByEmail(email).orElseThrow());
    }

}
