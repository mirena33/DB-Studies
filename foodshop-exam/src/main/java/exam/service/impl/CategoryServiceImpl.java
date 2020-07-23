package exam.service.impl;

import exam.model.entity.Category;
import exam.model.entity.CategoryName;
import exam.repository.CategoryRepository;
import exam.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,
                                String.format("Description for %s", categoryName.name())));
                    });

        }
    }

    @Override
    public Category find(CategoryName categoryName) {
        return this.categoryRepository
                .findByCategoryName(categoryName)
                .orElse(null);
    }
}
