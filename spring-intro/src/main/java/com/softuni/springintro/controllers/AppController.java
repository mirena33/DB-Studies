package com.softuni.springintro.controllers;

import com.softuni.springintro.entities.Book;
import com.softuni.springintro.services.AuthorService;
import com.softuni.springintro.services.BookService;
import com.softuni.springintro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.math.BigDecimal;


@Controller
public class AppController implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        // seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        // 1. Books Titles by Age Restriction

        System.out.println("Enter age restriction:");
        this.bookService
                .getAllBooksByAgeRestriction(this.bufferedReader.readLine())
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);

        // 2. Golden Books

        this.bookService
                .getAllBooksByEditionTypeAndCopies("gold", 5000)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);

        // 3. Books by Price

        this.bookService
                .getAllBooksWithPriceLessThanAndGreaterThan(new BigDecimal(5), new BigDecimal(40))
                .forEach(b -> {
                    System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice());
                });

        // 4. Not Released Books

        System.out.println("Enter year:");
        this.bookService
                .getAllBooksByReleaseDateNotInYear(Integer.parseInt(this.bufferedReader.readLine()))
                .forEach(b -> {
                    System.out.printf("%s %n", b.getTitle());
                });

        // 5. Books Released Before Date

        System.out.println("Enter release date:");
        this.bookService.getAllBooksByReleaseDateBefore(this.bufferedReader.readLine())
                .forEach(b -> {
                    System.out.printf("%s %s %.2f%n",
                            b.getTitle(),
                            b.getEditionType(),
                            b.getPrice());
                });

        // 6. Authors Search

        System.out.println("Enter end string:");
        this.authorService.getAuthorsFirstNameEndsWith(this.bufferedReader.readLine())
                .forEach(a -> {
                    System.out.printf("%s %s %n", a.getFirstName(), a.getLastName());
                });

        // 7. Books Search

        System.out.println("Enter containing string:");
        this.bookService.getAllBooksByTitleContainsString(this.bufferedReader.readLine())
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);

        // 8. Book Titles Search

        System.out.println("Enter start string:");
        this.bookService.getAllBooksByAuthorLastNameStartingWith(this.bufferedReader.readLine())
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);

        // 9. Count Books

        System.out.println("Enter given number:");
        System.out.println(this.bookService.getAllBooksWithTitleLongerThan(Integer.parseInt(this.bufferedReader.readLine())));

        // 10. Total Book Copies

        System.out.println("Enter author full name:");
        System.out.println(this.bookService.getTotalCopiesByAuthor(this.bufferedReader.readLine()));

        // 12. * Increase Book Copies

        System.out.println("Enter date and copies:");
        String date = this.bufferedReader.readLine();
        int copies = Integer.parseInt(this.bufferedReader.readLine());
        int totalCopies = this.bookService.updateBooksCopiesAfterDate(date, copies) * copies;
        System.out.println(totalCopies);
    }
}
