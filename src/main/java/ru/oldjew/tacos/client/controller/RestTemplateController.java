package ru.oldjew.tacos.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.oldjew.tacos.model.Ingredient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestTemplateController {

    RestTemplate restTemplate = new RestTemplate();

    public Ingredient getIngredientById(String id){
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, id);
    }

    public Ingredient getIngredientByIdViaURI(String ingredientId){
        Map<String, String> urlValues = new HashMap<>();
        urlValues.put("id", ingredientId);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/ingredients/{id}")
                .build(urlValues);
        return restTemplate.getForObject(uri, Ingredient.class);
    }

    public Ingredient getIngredientByIdLogDate(String ingredientId){
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.getForEntity("http://localhost:8080/ingredients/{id}",
                        Ingredient.class, ingredientId);
        log.info("Fetched time: {}", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    /**
     * put method
     */
    public void updateIngredient(Ingredient ingredient){
        restTemplate.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    /**
     * delete method
     */

    public void deleteIngredient(Ingredient ingredient){
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    /**
     * post new ingredient
     */
    public Ingredient postIngredient(Ingredient ingredient){
        return restTemplate.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }
}
