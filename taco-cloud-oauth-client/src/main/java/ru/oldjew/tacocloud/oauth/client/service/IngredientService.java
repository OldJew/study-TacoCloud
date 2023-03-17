package ru.oldjew.tacocloud.oauth.client.service;

import ru.oldjew.tacocloud.oauth.client.model.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);
}
