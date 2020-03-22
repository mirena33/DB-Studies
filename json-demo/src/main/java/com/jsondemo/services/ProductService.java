package com.jsondemo.services;

import com.jsondemo.models.dtos.ProductInRangeDto;
import com.jsondemo.models.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> getAllProductsInRange();
}
