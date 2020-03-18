package com.softuni.demo.services;

import com.softuni.demo.domain.dtos.UserLoginDto;
import com.softuni.demo.domain.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggedUserAdmin();
}
