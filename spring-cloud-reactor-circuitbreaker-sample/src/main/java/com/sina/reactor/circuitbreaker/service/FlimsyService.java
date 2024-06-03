package com.sina.reactor.circuitbreaker.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

/**
 * @author sinaaskarnejad
 */
@Service
public class FlimsyService {

    private final Random random = new Random();

    public Mono<Integer> brokenJob() {
        if (random.nextBoolean()) {
            return Mono.error(new RuntimeException("broken job"));
        }
        return Mono.fromSupplier(random::nextInt);
    }
}