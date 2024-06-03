package com.sina.circuitbreaker.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnErrorEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnSuccessEvent;
import io.github.resilience4j.core.EventConsumer;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author sinaaskarnejad
 */
@Configuration
public class Config {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> customizer() {
        return factory -> {
            //method 1
            factory.configureDefault(
                    id -> new Resilience4JConfigBuilder(id)
                            .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
                            .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                    .minimumNumberOfCalls(5)
                                    .failureRateThreshold(10).build())
                            .build()
            );

            //method 2
            factory.configure(builder -> builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build()), "slow");

            //method 3
            factory.addCircuitBreakerCustomizer(circuitBreaker -> circuitBreaker.
                    getEventPublisher().onError(onErrorEventEventConsumer()).onSuccess(onSuccessEventEventConsumer()), "event-circuit");
        };
    }

    @Bean
    public EventConsumer<CircuitBreakerOnErrorEvent> onErrorEventEventConsumer() {
        return event -> System.out.println("Error: " + event);
    }

    @Bean
    public EventConsumer<CircuitBreakerOnSuccessEvent> onSuccessEventEventConsumer() {
        return event -> System.out.println("Success: " + event);
    }
}