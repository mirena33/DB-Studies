package com.xmlexercise.repositories;

import com.xmlexercise.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNameAndBirthDate(String name, LocalDateTime date);

    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.youngDriver")
    List<Customer> findAllByBirthDateAndIsYoungDriver();
}
