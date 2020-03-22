package com.jsondemo.services;

import com.jsondemo.models.dtos.CategorySeedDto;
import com.jsondemo.models.entities.Category;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<Category> getRandomCategories();
}
