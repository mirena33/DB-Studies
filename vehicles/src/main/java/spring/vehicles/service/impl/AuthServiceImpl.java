package spring.vehicles.service.impl;

import spring.vehicles.exception.InvalidEntityException;
import spring.vehicles.model.Role;
import spring.vehicles.model.User;
import spring.vehicles.service.AuthService;
import spring.vehicles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Override
    public User register(User user) {
        if(user.getRoles().contains(Role.ADMIN)) {
            throw new InvalidEntityException("Admins can not self register.");
        }
        return userService.createUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userService.getUserByUsername(username);
        if(user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
