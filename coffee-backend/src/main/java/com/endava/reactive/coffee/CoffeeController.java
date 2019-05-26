package com.endava.reactive.coffee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coffee")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping
    public Flux<Coffee> findAll() {
        return coffeeService.findAll();
    }

    @GetMapping("{id}")
    public Mono<Coffee> findById(@PathVariable String id) {
        return coffeeService.findById(id)
                .switchIfEmpty(Mono.error(CoffeeNotFoundException::new));
    }

    @GetMapping("/randomCoffee/{name}")
    public Mono<String> getRandomCoffeeFor(@PathVariable String name) {
        Mono<Long> totalCoffee = coffeeService.countCoffee();
        Mono<Integer> index = totalCoffee.map(total -> (int) (Math.abs(name.hashCode()) % total));
        // int index = (int) (Math.abs(name.hashCode()) % totalCoffee);
        return index.flatMap(position -> coffeeService.findAll()
                        .elementAt(position)
                .map(Coffee::getName)
                        .map(n -> '\n' + n + "\n\n"));
        // return coffeeService.findAll()
        //         .stream()
        //         .skip(index)
        //         .map(Coffee::getName)
        //         .map(n -> '\n' + n + "\n\n")
        //         .findFirst()
        //         .orElse("No coffee for you!");
    }
}
