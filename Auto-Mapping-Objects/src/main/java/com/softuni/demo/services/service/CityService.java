package com.softuni.demo.services.service;

import com.softuni.demo.services.dtos.CityDto;
import com.softuni.demo.services.dtos.CitySeedDto;

public interface CityService {
    void save(CitySeedDto city);

    CityDto findByName(String name);
}
