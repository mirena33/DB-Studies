package com.springdata.restdemo.service;

import com.springdata.restdemo.model.Post;

import java.util.Collection;

public interface PostService {
    Collection<Post> getPosts();

    Post addPost(Post post);

    Post updatePost(Post post);

    Post deletePost(Long id);

    long getPostsCount();

    Post createPost(Post post);
}
