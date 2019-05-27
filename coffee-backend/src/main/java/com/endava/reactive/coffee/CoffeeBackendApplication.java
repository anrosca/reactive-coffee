package com.endava.reactive.coffee;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoffeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CoffeeRepository repository) {
        return args -> {
            repository.deleteAll();
            Stream.of("Cappuccino", "Espresso", "Latte")
                    .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                    .map(repository::save)
                    .forEach(System.out::println);
        };
    }
}
