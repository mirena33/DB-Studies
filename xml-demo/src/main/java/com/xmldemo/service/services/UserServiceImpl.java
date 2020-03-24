package com.xmldemo.service.services;

import com.xmldemo.data.entities.Phone;
import com.xmldemo.data.entities.User;
import com.xmldemo.data.repositories.PhoneRepository;
import com.xmldemo.data.repositories.UserRepository;
import com.xmldemo.service.dtos.PhoneDto;
import com.xmldemo.service.dtos.UserDto;
import com.xmldemo.service.dtos.UserRootDto;
import com.xmldemo.service.dtos.UserSeedDto;
import com.xmldemo.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final XMLParser xmlParser;
    private final PhoneRepository phoneRepository;
    private final String USERS_FILE_PATH = "src/main/resources/xmls/Users.xml";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, XMLParser xmlParser, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public void save(UserSeedDto userSeedDto) {
        this.userRepository.save(this.modelMapper.map(userSeedDto, User.class));
    }

    @Override
    public void seedUsers() throws JAXBException, FileNotFoundException {
        UserRootDto dtos = this.xmlParser.importFromXML(UserRootDto.class, USERS_FILE_PATH);

        for (UserDto userDto : dtos.getUsers()) {
            User user = this.modelMapper.map(userDto, User.class);
            this.userRepository.save(user);
            Set<Phone> phones = new LinkedHashSet<>();

            for (PhoneDto phoneDto : userDto.getPhoneRootDto().getPhoneDtos()) {
                Phone phone = this.phoneRepository.save(this.modelMapper.map(phoneDto, Phone.class));
                phone.setUser(user);
                phones.add(phone);
            }

            user.setPhones(phones);
            this.userRepository.save(user);
        }
    }

    @Override
    public List<UserDto> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto findById(long id) {
        User user = this.userRepository.findById(id).orElse(null);
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @Override
    public void exportUsers() throws JAXBException {
        List<UserDto> users = this.findAll();
        UserRootDto userRootDto = new UserRootDto();
        userRootDto.setUsers(users);

        this.xmlParser.exportToXML(userRootDto, USERS_FILE_PATH);

    }
}
