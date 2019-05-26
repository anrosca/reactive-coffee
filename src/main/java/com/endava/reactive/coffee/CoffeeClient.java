package com.endava.reactive.coffee;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CoffeeClient {

    private final WebClient webClient;

    public CoffeeClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081").build();
    }

    public Flux<Coffee> getAll() {
        return webClient.get()
                .uri("/coffee")
                .retrieve()
                .bodyToFlux(Coffee.class);
    }

    public Mono<Coffee> getById(String id) {
        return webClient.get()
                .uri("/coffee/{id}", id)
                .retrieve()
                .bodyToMono(Coffee.class);
    }

    public Mono<String> randomCoffeeFor(String name) {
        return webClient.get()
                .uri("/coffee/randomCoffee/{name}", name)
                .retrieve()
                .bodyToMono(String.class);
    }
}
