package com.endava.reactive.coffee;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoffeeRepository extends MongoRepository<Coffee, String> {

}
