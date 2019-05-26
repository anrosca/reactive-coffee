package com.endava.reactive.coffee;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    @Id
    private String id;

    private String name;

    public Coffee(final String name) {
        this.name = name;
    }
}
