package com.softuni.demo.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeSeedDto {
    private String firstName;
    private String lastName;
    private double salary;
    private String addressCity;
}
