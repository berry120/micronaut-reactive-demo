package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import java.time.Duration;

@Log4j2
@Controller("/")
public class TestController {

    @Get(value = "/carts/reactive", produces = MediaType.APPLICATION_JSON_STREAM)
    public Flux<Cart> getCartsReactive() {
        return Flux.range(1, 10)
                .map(integer -> Cart.builder().name("cart" + integer).build())
                .delayElements(Duration.ofMillis(1000))
                .doOnNext(cart -> log.info("Returning Flux of cart: " + cart));
    }
}