package com.softuni.demo;

import com.softuni.demo.services.dtos.CityDto;
import com.softuni.demo.services.dtos.CitySeedDto;
import com.softuni.demo.services.dtos.EmployeeSeedDto;
import com.softuni.demo.services.service.CityService;
import com.softuni.demo.services.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdRunner implements CommandLineRunner {
    private final EmployeeService employeeService;
    private final CityService cityService;

    @Autowired
    public CmdRunner(EmployeeService employeeService, CityService cityService) {
        this.employeeService = employeeService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {

//        CitySeedDto city1 = new CitySeedDto("Sofia");
//        CitySeedDto city2 = new CitySeedDto("Plovidv");
//        CitySeedDto city3 = new CitySeedDto("Varna");
//
//        this.cityService.save(city1);
//        this.cityService.save(city2);
//        this.cityService.save(city3);

//        EmployeeSeedDto employeeSeedDto = new EmployeeSeedDto("Petar", "Petrov", 1500, "Sofia");
//        EmployeeSeedDto employeeSeedDto2 = new EmployeeSeedDto("Ivan", "Ivanov", 1500, "Varna");
//        this.employeeService.save(employeeSeedDto);
//        this.employeeService.save(employeeSeedDto2);


    }
}
