package com.endava.reactive.coffee;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class CoffeeDetailsRepository {

    private static final Map<String, String> coffeeDetails = new HashMap<>();

    static {
        coffeeDetails.put("81ace8b3",
                "Traditionally prepared with 1/3 part espresso, 1/3 part steamed milk and 1/3 part foam in a small cappuccino cup which are typically 4-6 oz. in capacity. Cappuccino can be garnished with a light sprinkle of ground chocolate, cocoa powder, cinnamon, or nutmeg if desired. To prepare flavored cappuccino, simply add some quality coffee syrup such as Monin to taste.");
        coffeeDetails.put("88b29ada",
                "A concentrated drink produced by forcing hot water through finely ground coffee with an espresso machine for a brewed quantity of approximately 1 to 1-1/2 oz. A perfect espresso is deliciously smooth, with full body, intense aroma and a velvety crema. Best served in a pre-heated espresso cup. Please know, the proper spelling and pronunciation is \"espresso\" and not \"expresso\".");
        coffeeDetails.put("45cd430",
                "A double-shot of espresso mixed with approximately 5 oz. of steamed milk, typically little or no foam is added. Served in a large latte cup which are typically 9-12 oz. in capacity. To prepare flavored latte, simply add some quality coffee syrup such as Monin to taste.");
    }

    public Mono<String> getDetailsFor(String coffeeId) {
        return Mono.just(coffeeDetails.getOrDefault(coffeeId.toLowerCase(), "No details for you!"));
    }
}
