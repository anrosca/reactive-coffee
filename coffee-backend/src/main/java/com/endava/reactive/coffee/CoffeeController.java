package com.endava.reactive.coffee;

import java.time.Duration;
import java.util.Arrays;
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

    @GetMapping("/foo")
    public Flux<String> f() {
        return Mono.delay(Duration.ofMillis(100))
                .doOnNext(item -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .thenMany(foo());
    }

    private Flux<String> foo() {
        return bar()
                .map(i -> '\n' + i + "\n\n");
    }

    private Flux<String> bar() {
        return Flux.fromIterable(baz())
                .delayElements(Duration.ofSeconds(1));
    }

    private List<String> baz() {
        return Arrays.asList("1", "2", "3");
    }

    @GetMapping("{id}/details")
    public Mono<String> getCoffeeDetails(@PathVariable String id) {
        return coffeeService.getDetailsFor(id);
    }

    @GetMapping("/randomCoffee/{name}")
    public Mono<String> getRandomCoffeeFor(@PathVariable String name) {
        return coffeeService.getRandomCoffeeFor(name);
    }
}
