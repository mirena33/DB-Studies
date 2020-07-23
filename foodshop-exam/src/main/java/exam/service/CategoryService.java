package exam.service;

import exam.model.entity.Category;
import exam.model.entity.CategoryName;

public interface CategoryService {

    void initCategories();

    Category find(CategoryName categoryName);


}
