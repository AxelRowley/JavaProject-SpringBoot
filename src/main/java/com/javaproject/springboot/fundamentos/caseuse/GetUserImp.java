package com.javaproject.springboot.fundamentos.caseuse;

import com.javaproject.springboot.fundamentos.entity.User;
import com.javaproject.springboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImp implements GetUser{
    private UserService userService;

    public GetUserImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
