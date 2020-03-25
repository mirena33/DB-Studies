package com.xmlexercise.services.impl;

import com.xmlexercise.models.entities.Sale;
import com.xmlexercise.repositories.SalesRepository;
import com.xmlexercise.services.CarService;
import com.xmlexercise.services.CustomerService;
import com.xmlexercise.services.SaleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SalesRepository salesRepository;
    private final CustomerService customerService;
    private final CarService carService;
    private final Random random;

    public SaleServiceImpl(SalesRepository salesRepository, CustomerService customerService, CarService carService, Random random) {
        this.salesRepository = salesRepository;
        this.customerService = customerService;
        this.carService = carService;
        this.random = random;
    }

    @Override
    public void seedSales() {
        for (int i = 0; i < 20; i++) {
            Sale sale = new Sale();

            sale.setDiscount(this.setRandomDiscount());
            sale.setCar(this.carService.getRandomCar());
            sale.setCustomer(this.customerService.getRandomCustomer());

            this.salesRepository.saveAndFlush(sale);
        }

    }

    private Double setRandomDiscount() {
        Double[] discounts = new Double[]{0D, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
        return discounts[this.random.nextInt(discounts.length)];
    }
}
