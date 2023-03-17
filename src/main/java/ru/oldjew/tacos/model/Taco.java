package ru.oldjew.tacos.model;

import lombok.Data;
import ru.oldjew.tacos.repository.IngredientRef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    private List<Ingredient> ingredients;


    public Taco setName(String name) {
        this.name = name;
        return this;
    }

    public Taco setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<IngredientRef> getIngredientRefs(){
        List<IngredientRef> ingredientRefs = new ArrayList<>();
        for (Ingredient ingredient : ingredients){
            ingredientRefs.add(new IngredientRef(ingredient.getId()));
        }
        return ingredientRefs;
    }
}
