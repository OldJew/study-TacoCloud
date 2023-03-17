package ru.oldjew.tacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.oldjew.tacos.model.Ingredient;
import ru.oldjew.tacos.model.Ingredient.*;
import ru.oldjew.tacos.model.Taco;
import ru.oldjew.tacos.repository.IngredientRepository;
import ru.oldjew.tacos.repository.TacoRepository;

import java.util.Arrays;

@Component
@Slf4j
public class ApplicationConfig {

    @Bean
    public ApplicationRunner dataLoader(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        return args -> {
            log.info("Start load base ingredients and tacos");
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
            Ingredient bbqChicken = new Ingredient("BBCH", "Chicken BBQ", Type.PROTEIN);
            Ingredient dicedTomato = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
            Ingredient monterreyJack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(bbqChicken);
            ingredientRepository.save(dicedTomato);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(monterreyJack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            Taco meatTaco = new Taco();
            meatTaco.setName("Mega Meat").setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, bbqChicken,
                    cheddar, monterreyJack, salsa));
            Taco vegTaco = new Taco();
            vegTaco.setName("Vegetable taco").setIngredients(Arrays.asList(cornTortilla, dicedTomato, lettuce, salsa));

            Taco firmTaco = new Taco();
            firmTaco.setName("Firm taco").setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, bbqChicken,
                    cheddar, monterreyJack, dicedTomato, lettuce, salsa, sourCream));

            tacoRepository.save(meatTaco);
            tacoRepository.save(vegTaco);
            tacoRepository.save(firmTaco);
            log.info("Load ends");
        };
    }
}
