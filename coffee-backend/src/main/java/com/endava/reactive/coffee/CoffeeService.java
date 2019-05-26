package com.endava.reactive.coffee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findById(String id) {
        return coffeeRepository.findById(id);
    }

    public long countCoffee() {
        return coffeeRepository.count();
    }
}
