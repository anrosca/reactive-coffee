package com.endava.reactive.coffee;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CoffeeClient {

    private final RestTemplate restTemplate;

    public CoffeeClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.rootUri("http://localhost:8081").build();
    }

    public List<Coffee> getAll() {
        return Arrays.asList(restTemplate.getForObject("/coffee", Coffee[].class));
    }

    public Coffee getById(String id) {
        return restTemplate.getForObject("/coffee/{id}", Coffee.class, id);
    }

    public String randomCoffeeFor(String name) {
        return restTemplate.getForObject("/coffee/randomCoffee/{name}", String.class, name);
    }
}
