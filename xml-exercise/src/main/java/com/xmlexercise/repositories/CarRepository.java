package com.xmlexercise.repositories;

import com.xmlexercise.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByMakeAndModelAndTravelledDistance(String make, String mode, Long distance);
}
