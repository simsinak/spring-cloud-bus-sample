package com.sina.circuitbreaker;

import com.sina.circuitbreaker.service.FlimsyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author sinaaskarnejad
 */
@SpringBootApplication
@RequiredArgsConstructor
public class CircuitBreakerSample {

    private final FlimsyService flimsyService;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerSample.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                try {
                    circuitBreakerFactory.create("test").run(flimsyService::brokenJob);
                    System.out.println("Successful Call");
                } catch (Exception e) {
                    System.out.println("Error happened with Exception class type:" + e.getCause().getClass().getSimpleName());
                }
            }
        };
    }
}
