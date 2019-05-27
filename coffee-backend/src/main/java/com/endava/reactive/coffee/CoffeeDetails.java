package com.endava.reactive.coffee;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CoffeeDetails {

    @Id
    private String id;

    private String description;

    private String coffeeId;
}
