package com.xmlexercise.services;

import com.xmlexercise.models.dtos.SupplierSeedDto;
import com.xmlexercise.models.entities.Supplier;

import java.util.List;

public interface SupplierService {

    void seedSuppliers(List<SupplierSeedDto> supplierSeedDtos);

    Supplier getRandomSupplier();
}
