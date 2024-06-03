package com.sina.circuitbreaker.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author sinaaskarnejad
 */
@Service
public class FlimsyService {

    private final Random random = new Random();

    public int brokenJob() {
        if (random.nextBoolean()) {
            throw new RuntimeException("broken job");
        }
        return random.nextInt();
    }
}
