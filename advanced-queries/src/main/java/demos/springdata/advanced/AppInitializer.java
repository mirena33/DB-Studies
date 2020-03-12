package demos.springdata.advanced;

import demos.springdata.advanced.dao.IngredientRepository;
import demos.springdata.advanced.dao.LabelRepository;
import demos.springdata.advanced.dao.ShampooRepository;
import demos.springdata.advanced.entities.Label;
import demos.springdata.advanced.entities.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppInitializer implements ApplicationRunner {
    private final ShampooRepository shampooRepo;
    private final LabelRepository labelRepo;
    private final IngredientRepository ingredientRepo;

    @Autowired
    public AppInitializer(ShampooRepository shampooRepo, LabelRepository labelRepo, IngredientRepository ingredientRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1.
        shampooRepo.findBySize(Size.MEDIUM)
                .forEach(s -> System.out.printf("%s %s %s %.2f%n",
                        s.getLabel().getTitle(),
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));

        // 2.
        Label label = labelRepo.findOneById(10L);

        shampooRepo.findBySizeOrLabelOrderByPriceAsc(Size.MEDIUM, label)
                .forEach(s -> System.out.printf("%s %s %s %.2f%n",
                        s.getLabel().getTitle(),
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));

        // 3.
        shampooRepo.findByPriceGreaterThanOrderByPriceDesc(7)
                .forEach(s -> System.out.printf("%s %s %s %.2f%n",
                        s.getLabel().getTitle(),
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));

        // 4.
        shampooRepo.findWithIngredientsInList(List.of(ingredientRepo.findOneByName("Berry"),
                ingredientRepo.findOneByName("Mineral-Collagen")))
                .forEach(s -> System.out.printf("%s %s %s %.2f%n",
                        s.getLabel().getTitle(),
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));

        // 5.
        ingredientRepo.findByNameInOrderByPrice(List.of("Lavender", "Herbs", "Apple"));

        // 6.
        shampooRepo.findByCountOfIngredientsLowerThan(2)
                .forEach(s -> System.out.printf("%s %s %s %.2f%n",
                        s.getLabel().getTitle(),
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));
    }
}
