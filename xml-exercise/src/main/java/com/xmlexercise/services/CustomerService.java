package com.xmlexercise.services;

import com.xmlexercise.models.dtos.CustomerSeedDto;
import com.xmlexercise.models.dtos.CustomerViewRootDto;
import com.xmlexercise.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    void seedCustomers(List<CustomerSeedDto> customerSeedDtos);

    Customer getRandomCustomer();

    CustomerViewRootDto getAllOrderedCustomers();
}
