package com.endava.reactive.coffee;

import java.util.UUID;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionCallback;
import org.springframework.transaction.reactive.TransactionalOperator;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class CoffeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CoffeeService coffeeService,
            ReactiveTransactionManager transactionManager, CoffeeRepository repository) {
        return args -> {
            // coffeeService.save("Mocha").subscribe(System.out::println, throwable -> throwable.printStackTrace());

            TransactionalOperator tx = TransactionalOperator.create(transactionManager);

            coffeeService.deleteAll().thenMany(
                    Flux.just("Cappucino", "Espresso", "Moccaccino")
                            .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                            .flatMap(repository::save)
                            .as(tx::transactional))
                    .subscribe(System.out::println, throwable -> throwable.printStackTrace());
        };
    }
}
