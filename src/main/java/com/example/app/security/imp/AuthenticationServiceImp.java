package com.example.app.security.imp;

import com.example.app.dto.JwtAuthenticationResponseDto;
import com.example.app.dto.SignInRequestDto;
import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Role;
import com.example.app.entity.Users;
import com.example.app.repo.UsersRepository;
import com.example.app.security.AuthenticationService;
import com.example.app.security.JwtService;
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

    private final UsersRepository usersRepository;
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
        return usersRepository.save(users);
    }

    @Override
    public JwtAuthenticationResponseDto singin(SignInRequestDto signInRequestDto) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(),
                 signInRequestDto.getPassword()));

         var users= usersRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
         var jwt=jwtService.generateToken(new HashMap<>(),users);

         JwtAuthenticationResponseDto jwtAuthenticationResponseDto=new JwtAuthenticationResponseDto();
         jwtAuthenticationResponseDto.setToken(jwt);
         return jwtAuthenticationResponseDto;
    }

}
