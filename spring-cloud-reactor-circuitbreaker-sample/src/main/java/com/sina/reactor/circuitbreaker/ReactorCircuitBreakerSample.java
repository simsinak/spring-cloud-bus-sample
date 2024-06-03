package com.sina.reactor.circuitbreaker;

import com.sina.reactor.circuitbreaker.service.FlimsyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @author sinaaskarnejad
 */
@SpringBootApplication
@RequiredArgsConstructor
public class ReactorCircuitBreakerSample {

    private final FlimsyService flimsyService;
    private final ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory;

    public static void main(String[] args) {
        SpringApplication.run(ReactorCircuitBreakerSample.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                Mono<Integer> test = circuitBreakerFactory.create("test").run(flimsyService.brokenJob());
                test.subscribe(value -> System.out.println("Value is: " + value),
                        error -> System.out.println("error happened with Exception class:" + error.getCause().getClass().getSimpleName()));
            }
        };
    }
}