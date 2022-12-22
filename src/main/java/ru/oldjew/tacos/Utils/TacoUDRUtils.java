package ru.oldjew.tacos.Utils;

import ru.oldjew.tacos.model.Ingredient;
import ru.oldjew.tacos.model.IngredientUDT;

public class TacoUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient){
        IngredientUDT ingredientUDT = new IngredientUDT(ingredient.getName(), ingredient.getType());
        return ingredientUDT;
    }
}
