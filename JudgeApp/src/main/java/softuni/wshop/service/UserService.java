package softuni.wshop.service;

import softuni.wshop.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> getAllUsernames();

    void addRole(String username, String role);
}
