package com.softuni.springintro.services;

import com.softuni.springintro.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category getCategoryById(long id);
}
