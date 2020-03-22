package com.springdata.restdemo.init;

import com.springdata.restdemo.model.Post;
import com.springdata.restdemo.model.User;
import com.springdata.restdemo.service.PostService;
import com.springdata.restdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    private static final List<Post> SAMPLE_POSTS = List.of(
            new Post("Welcome to Spring Data",
                    "Developing data access objects with Spring Data is easy.."),
            new Post("Reactive Spring Data",
                    "Check R2DBC for reactive JDBC API.."),
            new Post("Now in Spring 5",
                    "Webflux provided reactive and nonblocking web service implementation.")
    );

    private static final List<User> SAMPLE_USERS = List.of(
            new User("Default", "Admin", "admin", "admin"),
            new User("Ivan", "Petrov", "ivan", "ivan")
    );

    @Override
    public void run(String... args) throws Exception {
        SAMPLE_USERS.forEach(user -> userService.addUser(user));
        log.info("Created users: {}", userService.getUsers());

        SAMPLE_POSTS.forEach(post -> postService.addPost(post));
        log.info("Created posts: {}", postService.getPosts());
    }
}
