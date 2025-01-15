package com.example.app.controller;


import com.example.app.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @RequestMapping("/admin")
    public Object test(){
        Users users=new Users();
        users.setName("zhangsan");
        users.setPassword("<PASSWORD>");

        return users;
    }


}
