package com.endava.reactive.coffee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CoffeeRepository extends ReactiveMongoRepository<Coffee, String> {

}
