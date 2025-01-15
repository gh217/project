package com.example.app.service.imp;

import com.example.app.dto.SignUpRequestDto;
import com.example.app.entity.Role;
import com.example.app.entity.Users;
import com.example.app.repo.UsersRepo;
import com.example.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UsersRepo usersRepo;

    private final PasswordEncoder passwordEncoder;

    public Users sighUp(SignUpRequestDto signUpRequestDto){
        Users users=new Users();
        users.setEmail(signUpRequestDto.getEmail());
        users.setName(signUpRequestDto.getName());
        users.setPassword(signUpRequestDto.getPassword());
        if(signUpRequestDto.isAdmin())users.setRole(Role.ADMIN.name());
        if(signUpRequestDto.isUser())users.setRole(Role.USER.name());
        users.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

        System.out.println(users.getRole());
        return usersRepo.save(users);
    }

}
