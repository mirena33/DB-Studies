package com.xmlexercise.services.impl;

import com.xmlexercise.models.dtos.CustomerSeedDto;
import com.xmlexercise.models.dtos.CustomerViewDto;
import com.xmlexercise.models.dtos.CustomerViewRootDto;
import com.xmlexercise.models.entities.Customer;
import com.xmlexercise.repositories.CustomerRepository;
import com.xmlexercise.services.CustomerService;
import com.xmlexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Random random;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Random random) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.random = random;
    }

    @Override
    public void seedCustomers(List<CustomerSeedDto> customerSeedDtos) {
        customerSeedDtos.forEach(customerSeedDto -> {
            if (this.validationUtil.isValid(customerSeedDto)) {
                if (this.customerRepository.findByNameAndBirthDate(
                        customerSeedDto.getName(),
                        customerSeedDto.getBirthDate()) == null) {

                    Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
                    this.customerRepository.saveAndFlush(customer);

                } else {
                    System.out.println("Customer already exists in DB");
                }
            } else {
                this.validationUtil.violations(customerSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId = this.random.nextInt((int) this.customerRepository.count()) + 1;
        return this.customerRepository.getOne(randomId);
    }

    @Override
    public CustomerViewRootDto getAllOrderedCustomers() {
        CustomerViewRootDto customerViewRootDto = new CustomerViewRootDto();
        List<CustomerViewDto> customerViewDtos = this.customerRepository.findAllByBirthDateAndIsYoungDriver()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerViewDto.class))
                .collect(Collectors.toList());

        customerViewRootDto.setCustomers(customerViewDtos);
        return customerViewRootDto;
    }

}
