package com.springdata.restdemo.service.impl;

import com.springdata.restdemo.dao.UserRepository;
import com.springdata.restdemo.model.User;
import com.springdata.restdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with ID " + id + " not found")
                );
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with username " + username + " not found")
                );
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }

    @Override
    public long getUsersCount() {
        return 0;
    }
}
