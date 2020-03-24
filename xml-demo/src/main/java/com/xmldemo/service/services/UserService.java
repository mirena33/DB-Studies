package com.xmldemo.service.services;

import com.xmldemo.service.dtos.UserDto;
import com.xmldemo.service.dtos.UserSeedDto;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {

    void save(UserSeedDto userSeedDto);

    void seedUsers() throws JAXBException, FileNotFoundException;

    List<UserDto> findAll();

    UserDto findById(long id);

    void exportUsers() throws JAXBException;
}
