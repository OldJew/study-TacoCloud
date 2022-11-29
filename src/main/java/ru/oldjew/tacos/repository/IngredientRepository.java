package ru.oldjew.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.oldjew.tacos.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
