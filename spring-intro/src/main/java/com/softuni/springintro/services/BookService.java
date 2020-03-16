package com.softuni.springintro.services;

import com.softuni.springintro.entities.Book;
import com.softuni.springintro.entities.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> getAllBooksByAgeRestriction(String ageRestriction);

    List<Book> getAllBooksByEditionTypeAndCopies(String editionType, int copies);

    List<Book> getAllBooksWithPriceLessThanAndGreaterThan(BigDecimal num1, BigDecimal num2);

    List<Book> getAllBooksByReleaseDateNotInYear(int year);

    List<Book> getAllBooksByReleaseDateBefore(String date);

    List<Book> getAllBooksByTitleContainsString(String str);

    List<Book> getAllBooksByAuthorLastNameStartingWith(String startWith);

    int updateBooksCopiesAfterDate(String date, int copies);

    int getTotalCopiesByAuthor(String fullName);

    int getAllBooksWithTitleLongerThan(int num);

}
