package com.springdata.restdemo.service.impl;

import com.springdata.restdemo.dao.PostRepository;
import com.springdata.restdemo.dao.UserRepository;
import com.springdata.restdemo.exception.InvalidEntityException;
import com.springdata.restdemo.model.Post;
import com.springdata.restdemo.model.User;
import com.springdata.restdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Post> getPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post addPost(Post post) {
        Long authorId;
        if (post.getAuthor() != null && post.getAuthor().getId() != null) {
            authorId = post.getAuthor().getId();
        } else {
            authorId = post.getAuthorId();
        }

        if (authorId != null) {
            User author = this.userRepository.findById(authorId)
                    .orElseThrow(
                            () -> new InvalidEntityException("Author with ID=" + authorId + " does not exist."));
            post.setAuthor(author);
        }

        if (post.getCreated() == null) {
            post.setCreated(new Date());
        }
        post.setModified(post.getCreated());

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post deletePost(Long id) {
        return null;
    }

    @Override
    public long getPostsCount() {
        return 0;
    }

    @Override
    public Post createPost(Post post) {
        Long authorId;
        if (post.getAuthor() != null && post.getAuthor().getId() != null) {
            authorId = post.getAuthor().getId();
        } else {
            authorId = post.getAuthorId();
        }
        if (authorId != null) {
            User author = userRepository.findById(authorId)
                    .orElseThrow(() -> new InvalidEntityException("Author with ID=" + authorId + " does not exist."));
            post.setAuthor(author);
        }
        if (post.getCreated() == null) {
            post.setCreated(new Date());
        }
        post.setModified(post.getCreated());

        return postRepository.save(post);
    }
}
