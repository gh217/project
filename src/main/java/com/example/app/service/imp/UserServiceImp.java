package com.example.app.service.imp;

import com.example.app.repo.UsersRepo;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UsersRepo usersRepo;

//    @Override
//    public UserDetailsService userDetailsService(){
//
//        return (username)->{
//            return usersRepo.findByUsername(username)
//                    .orElseThrow(()->new UsernameNotFoundException("User not found"));
//        };
//    }

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usersRepo.findByEmail(username)
                    .orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }

}
