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
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping
    public List<Coffee> findAll() {
        return coffeeService.findAll();
    }

    @GetMapping("{id}")
    public Coffee findById(@PathVariable String id) {
        return coffeeService.findById(id)
                .orElseThrow(CoffeeNotFoundException::new);
    }

    @GetMapping("/randomCoffee/{name}")
    public String getRandomCoffeeFor(@PathVariable String name) {
        long totalCoffee = coffeeService.countCoffee();
        int index = (int) (Math.abs(name.hashCode()) % totalCoffee);
        return coffeeService.findAll()
                .stream()
                .skip(index)
                .map(Coffee::getName)
                .map(n -> '\n' + n + "\n\n")
                .findFirst()
                .orElse("No coffee for you!");
    }
}
