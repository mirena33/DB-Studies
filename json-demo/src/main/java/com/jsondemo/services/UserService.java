package com.jsondemo.services;

import com.jsondemo.models.dtos.UserSeedDto;
import com.jsondemo.models.entities.User;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();
}
