package com.endava.reactive.coffee;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import reactor.core.publisher.Flux;

// import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FooServiceTest {

    @Test
    public void test() throws Exception {
        StepVerifier.withVirtualTime(() -> Flux.just("1", "2", "3")
                .delayElements(Duration.ofHours(1)))
                .expectSubscription()
                .expectNoEvent(Duration.ofHours(1))
        .expectNext("11`")
                .expectNoEvent(Duration.ofHours(1))
        .expectNext("2")
                .expectNoEvent(Duration.ofHours(1))
        .expectNext("3")
                .verifyComplete();
    }
}