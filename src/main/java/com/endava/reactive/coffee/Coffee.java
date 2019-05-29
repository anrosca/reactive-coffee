package com.endava.reactive.coffee;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coffee {

    private String id;

    private String name;

    private String details;

    public Coffee(final String name) {
        this.name = name;
    }

    public Coffee(final String name, final String details) {
        this.name = name;
        this.details = details;
    }
}
