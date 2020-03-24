package com.xmldemo;

import com.xmldemo.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public CmdRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers();
    }
}
