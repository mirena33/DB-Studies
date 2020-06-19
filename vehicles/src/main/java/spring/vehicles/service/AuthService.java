package spring.vehicles.service;

import spring.vehicles.model.User;

public interface AuthService {
    User register(User user);
    User login(String username, String password);
}
