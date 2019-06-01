package com.endava.reactive.coffee;

import org.springframework.data.annotation.Id;
import org.springframework.scheduling.annotation.Schedules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    public static void main(String[] args) throws InterruptedException {

        Mono.just("Hello")
                .flatMap(message -> Mono.subscriberContext()
                        .map(context -> message + ", " + context.get("greeting")))
                .subscriberContext(context -> context.put("greeting", "world"))
                .subscribe(System.out::println);

        // Flux.just(1, 2, 3)
        //         .map(i -> i * i)
        //         .doOnNext(i -> {
        //             System.out.println("map^2 " + Thread.currentThread().getName());
        //         })
        //         .publishOn(Schedulers.elastic())
        //         .map(String::valueOf)
        //         .doOnNext(i -> {
        //             System.out.println("toString " + Thread.currentThread().getName());
        //         })
        //         .subscribeOn(Schedulers.single())
        //         .subscribe(System.out::println);
        //
        // Thread.sleep(1000);
    }

    @Id
    private String id;

    private String name;

    public Coffee(final String name) {
        this.name = name;
    }
}
