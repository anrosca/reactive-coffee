package com.endava.reactive.coffee;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coffee {

    @Id
    private String id;

    private String name;

    public Coffee(final String name) {
        this.name = name;
    }
}
