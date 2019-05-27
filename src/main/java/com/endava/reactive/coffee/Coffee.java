package com.endava.reactive.coffee;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coffee {

    @Id
    private String id;

    private String name;

    private String details;

    public Coffee(final String name) {
        this.name = name;
    }
}
