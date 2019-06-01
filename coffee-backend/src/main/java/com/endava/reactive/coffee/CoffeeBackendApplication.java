package com.endava.reactive.coffee;

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

import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class CoffeeBackendApplication {

    public static void main(String[] args) {
        // ReactorDebugAgent.init();
        // BlockHound.install();
        SpringApplication.run(CoffeeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CoffeeService coffeeService,
            ReactiveTransactionManager transactionManager) {
        return args -> {
            TransactionalOperator transactionalOperator =
                    TransactionalOperator.create(transactionManager);

            coffeeService.deleteAll().thenMany(
                    Flux.just("Cappuccino", "Espresso", "Latte")
                            .map(name -> new Coffee(Integer.toHexString(name.hashCode()), name))
                            .flatMap(coffeeService::save))
                    .as(transactionalOperator::transactional)
                            .subscribe(System.out::println,
                            throwable -> throwable.printStackTrace(),
                            () -> {
                                System.out.println("Done");
                            });
        };
    }
}
