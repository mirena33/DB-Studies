package com.xmlexercise.services.impl;

import com.xmlexercise.models.dtos.PartSeedDto;
import com.xmlexercise.models.entities.Part;
import com.xmlexercise.repositories.PartRepository;
import com.xmlexercise.services.PartService;
import com.xmlexercise.services.SupplierService;
import com.xmlexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;
    private final Random random;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, ValidationUtil validationUtil, SupplierService supplierService, Random random) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
        this.random = random;
    }

    @Override
    public void seedParts(List<PartSeedDto> partSeedRootDtos) {
        partSeedRootDtos.
                forEach(partSeedDto -> {
                    if (this.validationUtil.isValid(partSeedDto)) {
                        if (this.partRepository
                                .findByNameAndPrice(partSeedDto.getName(), partSeedDto.getPrice()) == null) {

                            Part part = this.modelMapper.map(partSeedDto, Part.class);
                            part.setSupplier(this.supplierService.getRandomSupplier());

                            this.partRepository.saveAndFlush(part);

                        } else {
                            System.out.println("Part already in DB");
                        }
                    } else {
                        this.validationUtil.violations(partSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::print);
                    }

                });
    }

    @Override
    public Set<Part> getRandomParts() {
        Set<Part> resultSet = new HashSet<>();
        int randomCounter = this.random.nextInt(10) + 10;

        for (int i = 0; i < randomCounter; i++) {
            long randomId = this.random.nextInt((int) this.partRepository.count()) + 1;
            resultSet.add(this.partRepository.getOne(randomId));
        }

        return resultSet;
    }
}
