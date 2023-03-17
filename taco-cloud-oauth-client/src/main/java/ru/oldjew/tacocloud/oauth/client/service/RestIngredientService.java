package ru.oldjew.tacocloud.oauth.client.service;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.oldjew.tacocloud.oauth.client.model.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RestIngredientService implements IngredientService{

    private RestTemplate restTemplate;

    public RestIngredientService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return Arrays.asList(restTemplate.getForObject(
                "http://localhost:8080/api/ingredients",
                Ingredient[].class));
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/ingredients",
                ingredient,
                Ingredient.class);
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor =
                (request, bytes, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + accessToken);
                    return execution.execute(request, bytes);
                };

        return interceptor;
    }

}
