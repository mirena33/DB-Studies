package com.softuni.springintro.controllers;

import com.softuni.springintro.entities.Book;
import com.softuni.springintro.services.AuthorService;
import com.softuni.springintro.services.BookService;
import com.softuni.springintro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        // Ex 1
        this.bookService.getAllBooksAfter2000();
        // Ex 3
        this.authorService.findAllAuthorsByCountOfBooks()
                .forEach(a -> {
                    System.out.printf("%s %s %d%n",
                            a.getFirstName(),
                            a.getLastName(),
                            a.getBooks().size());
                });

    }
}
