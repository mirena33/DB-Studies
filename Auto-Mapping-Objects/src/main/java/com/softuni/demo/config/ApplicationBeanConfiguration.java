package com.softuni.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

        // IF WE WANT TO MAKE CUSTOM MAPPING:
//        ModelMapper modelMapper = new ModelMapper();
//        PropertyMap<Employee, EmployeeViewDto> propertyMap = new PropertyMap<Employee, EmployeeViewDto>() {
//            @Override
//            protected void configure() {
//                map().setAddress(source.getCity().getName());
//            }
//        };
//        modelMapper().addMappings(propertyMap);
//        return modelMapper;
    }
}
