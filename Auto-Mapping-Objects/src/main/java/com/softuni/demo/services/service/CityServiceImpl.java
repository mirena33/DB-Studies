package com.softuni.demo.services.service;

import com.softuni.demo.data.entities.City;
import com.softuni.demo.data.repositories.CityRepository;
import com.softuni.demo.services.dtos.CityDto;
import com.softuni.demo.services.dtos.CitySeedDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CitySeedDto city) {
        this.cityRepository.save(this.modelMapper.map(city, City.class));
    }

    @Override
    public CityDto findByName(String name) {
        return this.modelMapper.map(this.cityRepository.findByName(name), CityDto.class);
    }
}
