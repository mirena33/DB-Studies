package com.softuni.demo.services.service;

import com.softuni.demo.data.entities.City;
import com.softuni.demo.data.entities.Employee;
import com.softuni.demo.data.repositories.EmployeeRepository;
import com.softuni.demo.services.dtos.CityDto;
import com.softuni.demo.services.dtos.EmployeeSeedDto;
import com.softuni.demo.services.dtos.EmployeeViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final CityService cityService;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository, CityService cityService) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.cityService = cityService;
    }

    @Override
    public void save(EmployeeSeedDto employeeSeedDto) {
        Employee employee = this.modelMapper.map(employeeSeedDto, Employee.class);
        CityDto city = this.cityService.findByName(employeeSeedDto.getAddressCity());
        employee.setCityName(this.modelMapper.map(city, City.class));
        this.employeeRepository.save(employee);
    }

    @Override
    public EmployeeViewDto findByFirstNameAndLastName(String firstName, String lastName) {

        return this.modelMapper
                .map(this.employeeRepository.findByFirstNameAndLastName(firstName, lastName), EmployeeViewDto.class);
    }
}
