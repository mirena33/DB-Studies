package com.softuni.springintro.repositories;

import com.softuni.springintro.entities.AgeRestriction;
import com.softuni.springintro.entities.Book;
import com.softuni.springintro.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanAndPriceGreaterThan(BigDecimal num1, BigDecimal num2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContains(String str);

    List<Book> findAllByAuthor_LastNameStartingWith(String startsWith);

    @Query("SELECT SUM(b.id) FROM Book AS b WHERE LENGTH(b.title) > :num")
    int findAllBooksTitleLongerThan(@Param("num") int num);

    @Query("SELECT SUM(b.copies) FROM Book AS b" +
            " WHERE CONCAT(b.author.firstName, ' ', b.author.lastName) = :fullName")
    int findAllCopiesByAuthor(@Param("fullName") String fullName);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateAllBooksAfterGivenDate(@Param("date") LocalDate date, @Param("copies") int copies);

}
