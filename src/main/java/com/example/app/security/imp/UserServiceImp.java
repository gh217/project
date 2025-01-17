package com.example.app.security.imp;

import com.example.app.repo.UsersRepository;
import com.example.app.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UsersRepository usersRepository;

//    @Override
//    public UserDetailsService userDetailsService(){
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
                return usersRepository.findByEmail(username)
                    .orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }

}
