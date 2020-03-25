package com.xmlexercise.services;

import com.xmlexercise.models.dtos.CarSeedDto;
import com.xmlexercise.models.entities.Car;

import java.util.List;

public interface CarService {

    void seedCar(List<CarSeedDto> carSeedDtos);

    Car getRandomCar();
}
