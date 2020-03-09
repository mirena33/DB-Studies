package com.softuni.springintro.services;

import com.softuni.springintro.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(long id);

    List<Author> findAllAuthorsByCountOfBooks();
}
