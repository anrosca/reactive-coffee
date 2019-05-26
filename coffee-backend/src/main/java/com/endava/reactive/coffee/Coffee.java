package com.endava.reactive.coffee;

import org.springframework.data.annotation.Id;

import javafx.scene.transform.Scale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    public static void main(String[] args) throws InterruptedException {
        Flux.just(1, 2, 3)
                .doOnNext(item -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("doOnNext: " + item + " " + threadName);
                })
                .map(item -> item * item)
                .doOnNext(item -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("doOnNext: " + item + " " + threadName);
                })
                .publishOn(Schedulers.elastic())
                .map(String::valueOf)
                .doOnNext(item -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("doOnNext: " + item + " " + threadName);
                })
                .subscribeOn(Schedulers.single());


        Flux.just("Hello")
                .flatMap(message -> Mono.subscriberContext()
                        .map(context -> message + context.get("greeting") + "!"))
                .subscriberContext(context -> context.put("greeting", "world"))
                .subscribe(System.out::println);




        Thread.sleep(1000);
    }

    @Id
    private String id;

    private String name;

    public Coffee(final String name) {
        this.name = name;
    }
}
