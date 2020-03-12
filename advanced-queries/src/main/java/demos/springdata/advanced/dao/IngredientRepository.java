package demos.springdata.advanced.dao;

import demos.springdata.advanced.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findOneByName(String name);

    List<Ingredient> findByNameInOrderByPrice(Iterable<String> names);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient AS i SET i.price = i.price * 1.10 WHERE i.name in :names")
    List<Ingredient> updatePriceIngredientsInListBy10Percent(@Param("names") Iterable<String> names);

}
