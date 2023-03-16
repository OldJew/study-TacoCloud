package ru.oldjew.tacos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.oldjew.tacos.model.Ingredient;
import ru.oldjew.tacos.repository.IngredientRepository;

@RestController
@RequestMapping(path="/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {

    private IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId){
        ingredientRepository.deleteById(ingredientId);
    }
}
