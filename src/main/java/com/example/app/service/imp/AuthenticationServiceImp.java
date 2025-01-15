package com.example.app.service.imp;

import com.example.app.dto.SignUpDto;
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

    public Users sighUp(SignUpDto signUpDto){
        Users users=new Users();
        users.setEmail(signUpDto.getEmail());
        users.setName(signUpDto.getName());
        users.setPassword(signUpDto.getPassword());
        if(signUpDto.isAdmin())users.setRole(Role.ADMIN);
        if(signUpDto.isUser())users.setRole(Role.USER);
        users.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return usersRepo.save(users);
    }

}
