package com.example.app.service.imp;

import com.example.app.dto.JwtAuthenticationResponseDto;
import com.example.app.dto.RefreshTokenRequestDto;
import com.example.app.dto.SignInRequestDto;
import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Role;
import com.example.app.entity.Users;
import com.example.app.repo.UsersRepo;
import com.example.app.service.AuthenticationService;
import com.example.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService {

    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Users sighUp(SignUpRequestDto signUpRequestDto){
        Users users=new Users();
        users.setEmail(signUpRequestDto.getEmail());
        users.setName(signUpRequestDto.getName());
        users.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

        if(signUpRequestDto.getUser()==1)users.setRole(Role.USER);
        if(signUpRequestDto.getAdmin()==1)users.setRole(Role.ADMIN);
        return usersRepo.save(users);
    }

    @Override
    public JwtAuthenticationResponseDto singin(SignInRequestDto signInRequestDto) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(),
                 signInRequestDto.getPassword()));

         var users=usersRepo.findByEmail(signInRequestDto.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
         var jwt=jwtService.generateToken(new HashMap<>(),users);

         JwtAuthenticationResponseDto jwtAuthenticationResponseDto=new JwtAuthenticationResponseDto();
         jwtAuthenticationResponseDto.setToken(jwt);
         return jwtAuthenticationResponseDto;
    }

}
