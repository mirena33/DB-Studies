package com.xmldemo.service.services;

import com.xmldemo.data.entities.Phone;
import com.xmldemo.data.repositories.PhoneRepository;
import com.xmldemo.service.dtos.PhoneDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, ModelMapper modelMapper) {
        this.phoneRepository = phoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(PhoneDto phoneDto) {
        this.phoneRepository.save(modelMapper.map(phoneDto, Phone.class));
    }
}
