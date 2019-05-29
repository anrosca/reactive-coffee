package com.endava.reactive.coffee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    private final CoffeeDetailsRepository coffeeDetailsRepository;

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findById(String id) {
        return coffeeRepository.findById(id);
    }

    public long countCoffee() {
        return coffeeRepository.count();
    }

    public void deleteAll() {
        coffeeRepository.deleteAll();
    }

    public List<Coffee> saveAll(Iterable<Coffee> coffee) {
        return coffeeRepository.saveAll(coffee);
    }

    public Coffee save(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public String getRandomCoffeeFor(final String name) {
        long totalCoffee = countCoffee();
        int index = (int) (Math.abs(name.hashCode()) % totalCoffee);
        return findAll()
                .stream()
                .skip(index)
                .map(Coffee::getName)
                .map(n -> '\n' + n + "\n\n")
                .findFirst()
                .orElse("No coffee for you!");
    }

    public String getDetailsFor(final String id) {
        return coffeeDetailsRepository.getDetailsFor(id);
    }
}
