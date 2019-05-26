package com.endava.reactive.coffee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.query.Criteria;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    private final DatabaseClient databaseClient;

    public Flux<Coffee> findAll() {
        return databaseClient.select()
                .from("coffee")
                .as(Coffee.class)
                .fetch()
                .all();
    }

    @Transactional
    public Flux<Coffee> saveAll(Flux<Coffee> coffee) {
        return coffeeRepository.saveAll(coffee);
    }

    public Mono<Integer> save(String name) {
        return databaseClient.insert().into(Coffee.class)
                .using(Mono.just(name).map(Coffee::new))
                .fetch().rowsUpdated();
    }

    public Mono<Coffee> findById(String id) {
        // return coffeeRepository.findById(id);
       return databaseClient.select().from(Coffee.class)
               .matching(Criteria.where("id").is(id))
               .fetch()
               .one();
    }

    public Mono<Long> countCoffee() {
        return databaseClient.execute()
                .sql("select count(*) as total_coffee from coffee")
                .fetch()
                .one()
                .map(result -> result.get("total_coffee"))
                .cast(Long.class);
    }

    @Transactional
    public Mono<Void> deleteAll() {
        return databaseClient.delete().from(Coffee.class)
                .then();
    }
}
