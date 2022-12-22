package ru.oldjew.tacos.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.oldjew.tacos.model.Ingredient;
import ru.oldjew.tacos.model.IngredientUDT;
import ru.oldjew.tacos.repository.IngredientRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        IngredientUDT ingredientUDT = new IngredientUDT(ingredient.getId(), ingredient.getType());
        return ingredientUDT;
    }
}
