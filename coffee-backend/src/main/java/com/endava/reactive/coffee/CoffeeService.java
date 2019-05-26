package com.endava.reactive.coffee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public Flux<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Mono<Coffee> findById(String id) {
        return coffeeRepository.findById(id);
    }

    public Mono<Long> countCoffee() {
        return coffeeRepository.count();
    }
}
