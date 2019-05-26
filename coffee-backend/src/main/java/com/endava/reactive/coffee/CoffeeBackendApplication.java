package com.endava.reactive.coffee;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class CoffeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CoffeeRepository repository) {
        return args -> {
            repository.deleteAll().thenMany(
                    Flux.just("Cappucino", "Espresso", "Moccaccino")
                            .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                            .flatMap(repository::save)

            ).subscribe(System.out::println, throwable -> throwable.printStackTrace());
        };
    }
}
