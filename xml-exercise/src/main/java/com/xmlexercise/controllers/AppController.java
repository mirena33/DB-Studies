package com.xmlexercise.controllers;

import com.xmlexercise.constants.GlobalConstants;
import com.xmlexercise.models.dtos.*;
import com.xmlexercise.services.*;
import com.xmlexercise.utils.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Component
public class AppController implements CommandLineRunner {

    private final XMLParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;


    @Autowired
    public AppController(XMLParser xmlParser, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();
        this.exercise1();

    }

    private void exercise1() throws JAXBException {
        CustomerViewRootDto customerViewRootDto = this.customerService.getAllOrderedCustomers();
        this.xmlParser.marshalToFile(GlobalConstants.EX_1_PATH, customerViewRootDto);
    }

    private void seedSales() {
        this.saleService.seedSales();
    }

    private void seedCustomers() throws JAXBException, FileNotFoundException {
        CustomerSeedRootDto customerSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.CUSTOMERS_FILE_PATH, CustomerSeedRootDto.class);

        this.customerService.seedCustomers(customerSeedRootDto.getCustomers());
    }

    private void seedCars() throws JAXBException, FileNotFoundException {
        CarSeedRootDto carSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.CARS_FILE_PATH, CarSeedRootDto.class);

        this.carService.seedCar(carSeedRootDto.getCars());
    }

    private void seedParts() throws JAXBException, FileNotFoundException {
        PartSeedRootDto partSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.PARTS_FILE_PATH, PartSeedRootDto.class);

        this.partService.seedParts(partSeedRootDto.getParts());
    }

    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        SupplierSeedRootDto supplierSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.SUPPLIERS_FILE_PATH, SupplierSeedRootDto.class);

        this.supplierService.seedSuppliers(supplierSeedRootDto.getSuppliers());
    }
}
