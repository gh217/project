package com.example.app.config;

import com.example.app.entity.Role;
import com.example.app.entity.Users;
import com.example.app.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddUsersCommanLiner implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {


        if(!usersRepository.findById(1L).isEmpty())return;
        Users users=new Users();

        users.setName("admin");
        users.setEmail("admin@gmail.com");
        users.setPassword(passwordEncoder.encode("mo"));
        users.setRole(Role.ADMIN);
        usersRepository.save(users);


        for(int i=2;i<=50;i++){
            users=new Users();
            users.setName("mo "+ i);
            users.setEmail("email "+i);
            users.setPassword(passwordEncoder.encode("mo"));
            usersRepository.save(users);
        }
    }

}
