package com.softuni.demo.services.service;

import com.softuni.demo.services.dtos.EmployeeSeedDto;
import com.softuni.demo.services.dtos.EmployeeViewDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void save(EmployeeSeedDto employeeSeedDto);

    EmployeeViewDto findByFirstNameAndLastName(String firstName, String lastName);
}
