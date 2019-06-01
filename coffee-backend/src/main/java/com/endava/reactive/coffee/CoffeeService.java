package com.endava.reactive.coffee;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.netty.handler.codec.http.cookie.Cookie;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
// @Transactional
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    private final DatabaseClient databaseClient;

    private final CoffeeDetailsRepository coffeeDetailsRepository;

    public Flux<Coffee> findAll() {
        return coffeeRepository.findAll();
        // return databaseClient.select().from(Coffee.class).fetch().all();
    }

    public Mono<Coffee> findById(String id) {
        // return coffeeRepository.findById(id);
        return databaseClient.select().from(Coffee.class)
                .matching(Criteria.where("id").is(id))
                .fetch().one();
    }

    public Mono<Long> countCoffee() {
        // return coffeeRepository.count();
        return databaseClient.execute()
                .sql("select count(*) from coffee")
                .as(Long.class).fetch().one();
    }

    public Mono<Void> deleteAll() {
        // return coffeeRepository.deleteAll();
        return databaseClient.delete().from(Coffee.class).then();
    }

    public Flux<Coffee> saveAll(Iterable<Coffee> coffee) {
        // return coffeeRepository.saveAll(coffee);
        return Flux.empty();
    }

    public Mono<Coffee> save(Coffee coffee) {
        // return coffeeRepository.save(coffee);
        return databaseClient.insert().into(Coffee.class).using(coffee)
                .fetch().one().then(Mono.just(coffee));
    }

    public Mono<String> getRandomCoffeeFor(final String name) {
        return countCoffee().map(total -> (int) (Math.abs(name.hashCode()) % total))
                .flatMap(index -> findAll()
                        .elementAt(index)
                        .map(Coffee::getName)
                        .map(n -> '\n' + n + "\n\n")
                        .switchIfEmpty(Mono.just("No coffee for you!")));
    }

    public Mono<String> getDetailsFor(final String id) {
        return coffeeDetailsRepository.getDetailsFor(id);
    }

}
