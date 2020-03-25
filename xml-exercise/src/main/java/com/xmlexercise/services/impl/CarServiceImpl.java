package com.xmlexercise.services.impl;

import com.xmlexercise.models.dtos.CarSeedDto;
import com.xmlexercise.models.entities.Car;
import com.xmlexercise.repositories.CarRepository;
import com.xmlexercise.services.CarService;
import com.xmlexercise.services.PartService;
import com.xmlexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PartService partService;
    private final Random random;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, PartService partService, Random random) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.partService = partService;
        this.random = random;
    }

    @Override
    public void seedCar(List<CarSeedDto> carSeedDtos) {
        carSeedDtos.forEach(carSeedDto -> {
            if (this.validationUtil.isValid(carSeedDto)) {
                if (this.carRepository
                        .findByMakeAndModelAndTravelledDistance(carSeedDto.getMake(), carSeedDto.getModel(), carSeedDto.getTravelledDistance()) == null) {

                    Car car = this.modelMapper.map(carSeedDto, Car.class);
                    car.setParts(this.partService.getRandomParts());

                    this.carRepository.saveAndFlush(car);

                } else {
                    System.out.println("Car already in DB");
                }

            } else {
                this.validationUtil.violations(carSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });
    }

    @Override
    public Car getRandomCar() {
        long randomId = this.random.nextInt((int) this.carRepository.count()) + 1;
        return this.carRepository.getOne(randomId);
    }
}
