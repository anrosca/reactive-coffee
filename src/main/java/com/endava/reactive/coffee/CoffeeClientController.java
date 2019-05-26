package com.endava.reactive.coffee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coffee")
@RequiredArgsConstructor
public class CoffeeClientController {

    private final CoffeeClient coffeeClient;

    @GetMapping
    public List<Coffee> findAll() {
        return coffeeClient.getAll();
    }

    @GetMapping("{id}")
    public Coffee findById(@PathVariable String id) {
        return coffeeClient.getById(id);
    }

    @GetMapping("/randomCoffee/{name}")
    public String getRandomCoffeeFor(@PathVariable String name) {
        return coffeeClient.randomCoffeeFor(name);
    }
}
