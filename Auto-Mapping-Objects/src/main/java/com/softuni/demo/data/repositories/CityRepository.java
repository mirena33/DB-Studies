package com.softuni.demo.data.repositories;

import com.softuni.demo.data.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}
